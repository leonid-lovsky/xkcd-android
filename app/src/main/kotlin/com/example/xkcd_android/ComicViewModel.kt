package com.example.xkcd_android

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
    private val service: ComicService,
    private val dao: ComicDao,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    private val _currentComicNumber = MutableLiveData<Int>()
    private val _loading = MutableLiveData<Boolean>()
    private val _message = MutableLiveData<String>()
    private val _latestComicNumber = MutableLiveData<Int>()

    val loading = _loading as LiveData<Boolean>
    val comic = _currentComicNumber.switchMap { number -> getComicLiveData(number) }
    val message = _message as LiveData<String>

    fun getComicLiveData(number: Int): LiveData<Comic> {
        Timber.i("${this::class.simpleName}")
        viewModelScope.launch {
            val comic = dao.getComic(number)
            Timber.d(comic.toString())
            if (comic == null) {
                refreshComic(number)
            }
        }
        return dao.getComicLiveData(number)
    }

    fun getLatestComicLiveData(): LiveData<Comic> {
        Timber.i("${this::class.simpleName}")
        val number = sharedPreferences.getInt("latest_comic_number", 1)
        viewModelScope.launch {
            refreshLatestComic()
        }
        return dao.getComicLiveData(number)
    }

    suspend fun refreshComic(number: Int) {
        Timber.i("${this::class.simpleName}")
        try {
            _loading.value = true
            val response = service.getComic(number)
            Timber.d(response.toString())
            val body = response.body()
            Timber.d(body.toString())
            if (body != null) {
                dao.putComic(body)
            }
        } catch (e: Throwable) {
            Timber.e(e)
        } finally {
            _loading.value = false
        }
    }

    suspend fun refreshLatestComic() {
        Timber.i("${this::class.simpleName}")
        try {
            _loading.value = true
            val response = service.getLatestComic()
            Timber.d(response.toString())
            val body = response.body()
            Timber.d(body.toString())
            if (body != null) {
                dao.putComic(body)
            }
        } catch (e: Throwable) {
            Timber.e(e)
        } finally {
            _loading.value = false
        }
    }

    fun toComic(number: Int) {
        Timber.i("${this::class.simpleName}")
        _currentComicNumber.value = number
    }

    fun toFirstComic() {
        Timber.i("${this::class.simpleName}")
        toComic(1)
    }

    fun toPreviousComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value
        if (currentComicNumber != null) {
            toComic(currentComicNumber - 1)
        }
    }

    fun refreshComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value
        if (currentComicNumber != null) {
            viewModelScope.launch {
                refreshComic(currentComicNumber)
            }
        }
    }

    fun toNextComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value
        if (currentComicNumber != null) {
            toComic(currentComicNumber + 1)
        }
    }

    fun toLastComic() {
        Timber.i("${this::class.simpleName}")
        val latestComicNumber = _latestComicNumber.value
        if (latestComicNumber != null) {
            toComic(latestComicNumber)
        }
    }

    fun toRandomComic() {
        Timber.i("${this::class.simpleName}")
        val latestComicNumber = _latestComicNumber.value
        if (latestComicNumber != null) {
            toComic(Random.nextInt(1, latestComicNumber))
        }
    }

    fun toLatestComic() {
        Timber.i("${this::class.simpleName}")
    }
}
