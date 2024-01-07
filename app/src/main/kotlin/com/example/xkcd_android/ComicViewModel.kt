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

    // TODO
    val comic = _currentComicNumber.switchMap { comicNumber ->
        Timber.d("${this::class.simpleName}")
        Timber.d("Comic number: ${comicNumber}")
        comicDao.getComicLiveDataByNumber(comicNumber)
    }

    val message = _message as LiveData<String>

    init {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = sharedPreferences.getInt("current_comic_number", 0)
        val latestComicNumber = sharedPreferences.getInt("0", 1)
        Timber.d("Current comic number: ${currentComicNumber}")
        Timber.d("Latest comic number: ${latestComicNumber}")
        _currentComicNumber.value = currentComicNumber
        _latestComicNumber.value = latestComicNumber
    }

    fun navigateToComicByNumber(comicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        val latestComicNumber = _latestComicNumber.value ?: return
        val desiredComicNumber = comicNumber
        Timber.d("Current comic number: ${currentComicNumber}")
        Timber.d("Latest comic number: ${latestComicNumber}")
        Timber.d("Desired comic number: ${desiredComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToLatestComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        val latestComicNumber = _latestComicNumber.value ?: return
        val desiredComicNumber = 0
        Timber.d("Current comic number: ${currentComicNumber}")
        Timber.d("Latest comic number: ${latestComicNumber}")
        Timber.d("Desired comic number: ${desiredComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToFirstComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        val latestComicNumber = _latestComicNumber.value ?: return
        val desiredComicNumber = 1
        Timber.d("Current comic number: ${currentComicNumber}")
        Timber.d("Latest comic number: ${latestComicNumber}")
        Timber.d("Desired comic number: ${desiredComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToPreviousComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        val latestComicNumber = _latestComicNumber.value ?: return
        val desiredComicNumber = currentComicNumber - 1
        Timber.d("Current comic number: ${currentComicNumber}")
        Timber.d("Latest comic number: ${latestComicNumber}")
        Timber.d("Desired comic number: ${desiredComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToCurrentComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        val latestComicNumber = _latestComicNumber.value ?: return
        val desiredComicNumber = currentComicNumber
        Timber.d("Current comic number: ${currentComicNumber}")
        Timber.d("Latest comic number: ${latestComicNumber}")
        Timber.d("Desired comic number: ${desiredComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToNextComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        val latestComicNumber = _latestComicNumber.value ?: return
        val desiredComicNumber = currentComicNumber + 1
        Timber.d("Current comic number: ${currentComicNumber}")
        Timber.d("Latest comic number: ${latestComicNumber}")
        Timber.d("Desired comic number: ${desiredComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToLastComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        val latestComicNumber = _latestComicNumber.value ?: return
        val desiredComicNumber = latestComicNumber
        Timber.d("Current comic number: ${currentComicNumber}")
        Timber.d("Latest comic number: ${latestComicNumber}")
        Timber.d("Desired comic number: ${desiredComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }

    fun navigateToRandomComic() {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        val latestComicNumber = _latestComicNumber.value ?: return
        val desiredComicNumber = (1..latestComicNumber).random()
        Timber.d("Current comic number: ${currentComicNumber}")
        Timber.d("Latest comic number: ${latestComicNumber}")
        Timber.d("Desired comic number: ${desiredComicNumber}")
        _currentComicNumber.value = desiredComicNumber
    }
}
