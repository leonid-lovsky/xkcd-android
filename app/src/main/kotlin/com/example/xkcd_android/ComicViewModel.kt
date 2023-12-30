package com.example.xkcd_android

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
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
    private val _loading = MutableLiveData<Boolean>()
    private val _comic = MutableLiveData<Comic>()
    private val _message = MutableLiveData<String>()
    private val _latestComicNumber = MutableLiveData<Int>()

    val loading = _loading as LiveData<Boolean>
    val comic = _currentComicNumber.switchMap { comicDao.getComicLiveData(it).distinctUntilChanged() }
    val message = _message as LiveData<String>

    fun getLatestComic() {
        Timber.i("${this::class.simpleName}")
    }

    fun fetchLatestComic() {
        Timber.i("${this::class.simpleName}")
        viewModelScope.launch {
            try {
                _loading.value = true
                val response = comicService.getLatestComic()
                Timber.d(response.toString())
                val body = response.body()
                Timber.d(body.toString())
                if (body != null) {
                    comicDao.putComic(body)
                }
            } catch (e: Throwable) {
                Timber.e(e)
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchComic(number: Int) {
        Timber.i("${this::class.simpleName}")
        viewModelScope.launch {
            try {
                _loading.value = true
                val response = comicService.getComic(number)
                Timber.d(response.toString())
                val body = response.body()
                Timber.d(body.toString())
                if (body != null) {
                    comicDao.putComic(body)
                }
            } catch (e: Throwable) {
                Timber.e(e)
            } finally {
                _loading.value = false
            }
        }
    }

    fun getComic(number: Int) {
        Timber.i("${this::class.simpleName}")
        _currentComicNumber.value = number
    }

    fun getFirstComic() {
        Timber.i("${this::class.simpleName}")
        _currentComicNumber.value = 1
    }

    fun getPreviousComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value
        if (currentComicNumber != null) {
            _currentComicNumber.value = currentComicNumber - 1
        }
    }

    fun refreshComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value
        if (currentComicNumber != null) {
            fetchComic(currentComicNumber)
        }
    }

    fun getNextComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value
        if (currentComicNumber != null) {
            _currentComicNumber.value = currentComicNumber + 1
        }
    }

    fun getLastComic() {
        Timber.i("${this::class.simpleName}")
        val latestComicNumber = _latestComicNumber.value
        if (latestComicNumber != null) {
            _currentComicNumber.value = latestComicNumber
        }
    }

    fun getRandomComic() {
        Timber.i("${this::class.simpleName}")
        val latestComicNumber = _latestComicNumber.value
        if (latestComicNumber != null) {
            _currentComicNumber.value = Random.nextInt(1, latestComicNumber)
        }
    }
}
