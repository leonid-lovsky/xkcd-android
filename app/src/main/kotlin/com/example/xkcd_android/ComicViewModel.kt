package com.example.xkcd_android

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val comicService: ComicService,
    private val comicDao: ComicDao,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    private val _currentComicNumber = MutableLiveData<Int>()
    private val _latestComicNumber = MutableLiveData<Int>()

    private val _isLoading = MutableLiveData<Boolean>()
    private val _message = MutableLiveData<String>()

    private fun getComicLiveDataByNumber(comicNumber: Int): LiveData<Comic> {
        Timber.d("Comic number: ${comicNumber}")
        viewModelScope.launch {
            val currentComic = comicDao.getComicByNumber(comicNumber)
            Timber.d("${currentComic}")
            if (currentComic == null) {
                fetchComicByNumber(comicNumber)
            }
        }
        return comicDao.getComicLiveDataByNumber(comicNumber)
    }

    val currentComic = _currentComicNumber.switchMap { currentComicNumber ->
        Timber.d("Current comic number: ${currentComicNumber}")
        getComicLiveData(currentComicNumber)
    }

    val latestComic = _latestComicNumber.switchMap { latestComicNumber ->
        Timber.d("Latest comic number: ${latestComicNumber}")
        getComicLiveData(latestComicNumber)
    }

    val isLoading = _isLoading as LiveData<Boolean>
    val message = _message as LiveData<String>

    init {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = sharedPreferences.getInt("current_comic_number", 0)
        Timber.d("${currentComicNumber}")
        val latestComicNumber = sharedPreferences.getInt("latest_comic_number", 0)
        Timber.d("${latestComicNumber}")
        if (currentComicNumber >= 1) {
            setComicNumberLiveData(currentComicNumber)
        }
        if (latestComicNumber >= 1) {
            setComicLatestNumberLiveData(latestComicNumber)
        }
    }

    private suspend fun fetchComicByNumber(comicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        Timber.i("Comic number: ${comicNumber}")
        try {
            _isLoading.value = true
            val response = if (comicNumber > 0) {
                comicService.requestComicByNumber(comicNumber)
            } else {
                comicService.requestLatestComic()
            }
            Timber.d("Response: ${response}")
            val body = response.body()
            Timber.d("Body: ${body}")
            if (body != null) {
                comicDao.putComic(body)
                putLatestComicNumber(body.num)
            }
        } catch (e: Throwable) {
            Timber.e(e)
        } finally {
            _isLoading.value = false
        }
    }

    fun navigateToComic(comicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        Timber.i("Comic number: ${comicNumber}")
        putComicNumber(comicNumber)
    }

    fun displayFirstComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("${currentComicNumber}")
        navigateToComic(1)
    }

    fun displayPreviousComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("${currentComicNumber}")
        navigateToComic(currentComicNumber - 1)
    }

    fun reloadComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("${currentComicNumber}")
        viewModelScope.launch {
            fetchComicByNumber(currentComicNumber)
        }
    }

    fun displayNextComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("${currentComicNumber}")
        navigateToComic(currentComicNumber + 1)
    }

    fun displayLastComic() {
        Timber.i("${this::class.simpleName}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("${latestComicNumber}")
        navigateToComic(latestComicNumber)
    }

    fun displayRandomComic() {
        Timber.i("${this::class.simpleName}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("${latestComicNumber}")
        navigateToComic(Random.nextInt(1, latestComicNumber))
    }

    fun displayLatestComic() {
        Timber.i("${this::class.simpleName}")
        viewModelScope.launch {
            refreshLatestComic()
        }
    }

    private fun getComicLiveData(comicNumber: Int): LiveData<Comic> {
        Timber.i("${this::class.simpleName}")
        Timber.d("${comicNumber}")
        return comicDao.getComicLiveDataByNumber(comicNumber)
    }

    private fun putComicNumber(comicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        Timber.d("${comicNumber}")
        setComicNumberLiveData(comicNumber)
        setComicNumberPreferences(comicNumber)
    }

    private fun putLatestComicNumber(comicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        setComicLatestNumberLiveData(comicNumber)
        setLatestComicNumberPreferences(comicNumber)
    }

    private fun setComicNumberLiveData(comicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        Timber.d("${comicNumber}")
        if (comicNumber > 0) {
            // TODO: out of range
            _currentComicNumber.value = comicNumber
        }
    }

    private fun setComicLatestNumberLiveData(comicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        Timber.d("${comicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: 0
        Timber.d("${latestComicNumber}")
        if (comicNumber > latestComicNumber) {
            // TODO: out of range
            _latestComicNumber.value = comicNumber
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun setComicNumberPreferences(comicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        Timber.d("${comicNumber}")
        if (comicNumber > 0) {
            // TODO: out of range
            sharedPreferences.edit().putInt("comic_number", comicNumber).apply()
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun setLatestComicNumberPreferences(comicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        Timber.d("${comicNumber}")
        val latestComicNumber = sharedPreferences.getInt("latest_comic_number", 0)
        Timber.d("${latestComicNumber}")
        if (comicNumber > latestComicNumber) {
            // TODO: out of range
            sharedPreferences.edit().putInt("latest_comic_number", latestComicNumber).apply()
        }
    }
}
