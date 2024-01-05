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

    // TODO: range
    private fun putCurrentComicNumberLiveData(newCurrentComicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        Timber.d("New current comic number: ${newCurrentComicNumber}")
        if (newCurrentComicNumber > 0) {
            _currentComicNumber.value = newCurrentComicNumber
        }
    }

    // TODO: range
    @SuppressLint("ApplySharedPref")
    private fun putCurrentComicNumberPreferences(newCurrentComicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        Timber.d("New current comic number: ${newCurrentComicNumber}")
        if (newCurrentComicNumber > 0) {
            sharedPreferences.edit().putInt("current_comic_number", newCurrentComicNumber).apply()
        }
    }

    // TODO: range
    private fun putLatestComicNumberLiveData(newLatestComicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        Timber.d("New latest comic number: ${newLatestComicNumber}")
        val savedLatestComicNumber = _latestComicNumber.value ?: 0
        Timber.d("Saved latest comic number: ${newLatestComicNumber}")
        if (newLatestComicNumber > savedLatestComicNumber) {
            _latestComicNumber.value = newLatestComicNumber
        }
    }

    // TODO: range
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

    private val _isLoading = MutableLiveData<Boolean>()
    private val _message = MutableLiveData<String>()

    val isLoading = _isLoading as LiveData<Boolean>
    val message = _message as LiveData<String>

    private val _currentComicNumber = MutableLiveData<Int>()
    private val _latestComicNumber = MutableLiveData<Int>()

    val comic = _currentComicNumber.switchMap { comicNumber ->
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
        comicDao.getComicLiveDataByNumber(comicNumber)
    }

    fun navigateToComicByNumber(comicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        navigateToComicByNumber(comicNumber)
    }

    fun navigateToLatestComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        navigateToComicByNumber(0)
    }

    fun navigateToFirstComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        navigateToComicByNumber(1)
    }

    fun navigateToPreviousComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        navigateToComicByNumber(currentComicNumber - 1)
    }

    fun navigateToCurrentComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        navigateToComicByNumber(currentComicNumber)
    }

    fun navigateToNextComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        navigateToComicByNumber(currentComicNumber + 1)
    }

    fun navigateToLastComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        navigateToComicByNumber(latestComicNumber)
    }

    fun navigateToRandomComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        navigateToComicByNumber(Random.nextInt(1, latestComicNumber))
    }

    init {
        val currentComicNumber = sharedPreferences.getInt("current_comic_number", 0)
        val latestComicNumber = sharedPreferences.getInt("latest_comic_number", 1)
        Timber.d("Current comic number: ${currentComicNumber}")
        Timber.d("Latest comic number: ${latestComicNumber}")
        putCurrentComicNumberLiveData(currentComicNumber)
        putLatestComicNumberLiveData(latestComicNumber)
    }
}
