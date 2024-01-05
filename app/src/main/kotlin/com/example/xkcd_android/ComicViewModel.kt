package com.example.xkcd_android

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _isRefreshing = MutableLiveData<Boolean>()

    private val _currentComicNumber = MutableLiveData<Int>()
    private val _latestComicNumber = MutableLiveData<Int>()

    private val _message = MutableLiveData<String>()

    val isRefreshing = _isRefreshing as LiveData<Boolean>

    val comic = _currentComicNumber.switchMap { comicNumber ->
        Timber.d("${this::class.simpleName}")
        Timber.d("Comic number: ${comicNumber}")
        comicDao.getComicLiveDataByNumber(comicNumber)
    }

    val message = _message as LiveData<String>

    init {
        val currentComicNumber = sharedPreferences.getInt("current_comic_number", LATEST_COMIC_NUMBER)
        Timber.d("Current comic number: ${currentComicNumber}")
        _currentComicNumber.value = currentComicNumber
        val latestComicNumber = sharedPreferences.getInt("latest_comic_number", FIRST_COMIC_NUMBER)
        Timber.d("Latest comic number: ${latestComicNumber}")
        _latestComicNumber.value = latestComicNumber
    }

    fun navigateToComicByNumber(comicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        val desiredComicNumber = comicNumber
        Timber.d("Desired comic number: ${latestComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToLatestComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        val desiredComicNumber = LATEST_COMIC_NUMBER
        Timber.d("Desired comic number: ${latestComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToFirstComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        val desiredComicNumber = FIRST_COMIC_NUMBER
        Timber.d("Desired comic number: ${latestComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToPreviousComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        val desiredComicNumber = currentComicNumber - 1
        Timber.d("Desired comic number: ${latestComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToCurrentComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        val desiredComicNumber = currentComicNumber
        Timber.d("Desired comic number: ${latestComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToNextComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        val desiredComicNumber = currentComicNumber + 1
        Timber.d("Desired comic number: ${latestComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToLastComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        val desiredComicNumber = latestComicNumber
        Timber.d("Desired comic number: ${latestComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToRandomComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        Timber.d("Current comic number: ${currentComicNumber}")
        val latestComicNumber = _latestComicNumber.value ?: return
        Timber.d("Latest comic number: ${latestComicNumber}")
        val desiredComicNumber = Random.nextInt(FIRST_COMIC_NUMBER, latestComicNumber)
        Timber.d("Desired comic number: ${latestComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    companion object {

        const val FIRST_COMIC_NUMBER = 1
        const val LATEST_COMIC_NUMBER = 0
    }
}
