package com.example.xkcd_android

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
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
    private val comicPreferences: SharedPreferences,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    private val _currentComic = MutableLiveData<Comic>()
    private val _message = MutableLiveData<String>()
    private val _latestComicNumber = MutableLiveData<Int>()

    val loading = _loading as LiveData<Boolean>
    val currentComic = _currentComic as LiveData<Comic>
    val message = _message as LiveData<String>

    init {
        // val currentPage_ = comicPreferences.getInt("current_page", -1)
        // if (currentPage_ == -1) {
        //     getLatestComic()
        // } else {
        //     getComic(currentPage_)
        // }
    }

    fun getLatestComic() {
        Timber.i("${this::class.simpleName}")
        viewModelScope.launch {
            try {
                _loading.value = true
            } catch (e: Throwable) {
                Timber.e(e)
            } finally {
                _loading.value = false
            }
        }
    }

    fun getComic(number: Int) {
        Timber.i("${this::class.simpleName}")
        viewModelScope.launch {
            try {
                _loading.value = true
            } catch (e: Throwable) {
                Timber.e(e)
            } finally {
                _loading.value = false
            }
        }
    }

    fun refreshComic(number: Int) {
        Timber.i("${this::class.simpleName}")
        viewModelScope.launch {
            try {
                _loading.value = true
                val response = comicService.getComic(number)
                Timber.d("response = ${response}")
                val body = response.body()
                Timber.d("body = ${body}")
                if (body != null) {
                    comicDao.putComic(body)
                }
                val comic = comicDao.getComic(number)
                Timber.d("comic = ${comic}")
                _currentComic.value = comic
            } catch (e: Throwable) {
                Timber.e(e)
            } finally {
                _loading.value = false
            }
        }
    }

    fun refreshComic() {
        Timber.i("${this::class.simpleName}")
        val currentComic_ = _currentComic.value ?: return
        refreshComic(currentComic_.num)
    }

    fun getRandomComic() {
        Timber.i("${this::class.simpleName}")
        val latestPage_ = _latestComicNumber.value ?: return
        getComic(Random.nextInt(1, latestPage_))
    }

    fun getFirstComic() {
        Timber.i("${this::class.simpleName}")
        getComic(1)
    }

    fun getLastComic() {
        Timber.i("${this::class.simpleName}")
        val latestPage_ = _latestComicNumber.value ?: return
        getComic(latestPage_)
    }

    fun getPreviousComic() {
        Timber.i("${this::class.simpleName}")
        val currentComic_ = _currentComic.value ?: return
        getComic(currentComic_.num - 1)
    }

    fun getNextComic() {
        Timber.i("${this::class.simpleName}")
        val currentComic_ = _currentComic.value ?: return
        getComic(currentComic_.num + 1)
    }
}
