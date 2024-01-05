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
            putCurrentComicNumberLiveData(currentComicNumber)
        }
        if (latestComicNumber >= 1) {
            putLatestComicNumberLiveData(latestComicNumber)
        }
    }

    // TODO
    private fun fetchComicByNumber(comicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        Timber.i("Comic number: ${comicNumber}")
        viewModelScope.launch {
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
    }

    fun navigateToComic(comicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        Timber.i("Comic number: ${comicNumber}")
        putCurrentComicNumber(comicNumber)
    }

    fun navigateToFirstComic() {
        Timber.i("${this::class.simpleName}")
        val savedCurrentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Saved current comic number: ${savedCurrentComicNumber}")
        putCurrentComicNumber(1)
    }

    fun navigateToPreviousComic() {
        Timber.i("${this::class.simpleName}")
        val savedCurrentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Saved current comic number: ${savedCurrentComicNumber}")
        putCurrentComicNumber(savedCurrentComicNumber - 1)
    }

    fun reloadCurrentComic() {
        Timber.i("${this::class.simpleName}")
        val savedCurrentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Saved current comic number: ${savedCurrentComicNumber}")
        fetchComicByNumber(savedCurrentComicNumber)
    }

    fun navigateToNextComic() {
        Timber.i("${this::class.simpleName}")
        val savedCurrentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Saved current comic number: ${savedCurrentComicNumber}")
        putCurrentComicNumber(savedCurrentComicNumber + 1)
    }

    // TODO
    fun navigateToLastComic() {
        Timber.i("${this::class.simpleName}")
        val savedLatestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Saved latest comic number: ${savedLatestComicNumber}")
        putCurrentComicNumber(savedLatestComicNumber)
    }

    fun navigateToRandomComic() {
        Timber.i("${this::class.simpleName}")
        val savedLatestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Saved latest comic number: ${savedLatestComicNumber}")
        putCurrentComicNumber(Random.nextInt(1, savedLatestComicNumber))
    }

    // TODO
    fun navigateToLatestComic() {
        Timber.i("${this::class.simpleName}")
        fetchComicByNumber(0)
    }

    private fun getComicLiveData(comicNumber: Int): LiveData<Comic> {
        Timber.i("${this::class.simpleName}")
        Timber.d("Comic number: ${comicNumber}")
        return comicDao.getComicLiveDataByNumber(comicNumber)
    }

    private fun putCurrentComicNumber(newCurrentComicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        Timber.d("New current comic number: ${newCurrentComicNumber}")
        putCurrentComicNumberLiveData(newCurrentComicNumber)
        putCurrentComicNumberPreferences(newCurrentComicNumber)
    }

    private fun putLatestComicNumber(newLatestComicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        Timber.d("New latest comic number: ${newLatestComicNumber}")
        putLatestComicNumberLiveData(newLatestComicNumber)
        putLatestComicNumberPreferences(newLatestComicNumber)
    }

    // TODO
    private fun putCurrentComicNumberLiveData(newCurrentComicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        Timber.d("New current comic number: ${newCurrentComicNumber}")
        if (newCurrentComicNumber > 0) {
            _currentComicNumber.value = newCurrentComicNumber
        }
    }

    // TODO
    @SuppressLint("ApplySharedPref")
    private fun putCurrentComicNumberPreferences(newCurrentComicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        Timber.d("New current comic number: ${newCurrentComicNumber}")
        if (newCurrentComicNumber > 0) {
            sharedPreferences.edit().putInt("current_comic_number", newCurrentComicNumber).apply()
        }
    }

    // TODO
    private fun putLatestComicNumberLiveData(newLatestComicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        Timber.d("New latest comic number: ${newLatestComicNumber}")
        val savedLatestComicNumber = _latestComicNumber.value ?: 0
        Timber.d("Saved latest comic number: ${newLatestComicNumber}")
        if (newLatestComicNumber > savedLatestComicNumber) {
            _latestComicNumber.value = newLatestComicNumber
        }
    }

    // TODO
    @SuppressLint("ApplySharedPref")
    private fun putLatestComicNumberPreferences(newLatestComicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        Timber.d("New latest comic number: ${newLatestComicNumber}")
        val savedLatestComicNumber = sharedPreferences.getInt("latest_comic_number", 0)
        Timber.d("Saved latest comic number: ${newLatestComicNumber}")
        if (newLatestComicNumber > savedLatestComicNumber) {
            sharedPreferences.edit().putInt("latest_comic_number", newLatestComicNumber).apply()
        }
    }
}
