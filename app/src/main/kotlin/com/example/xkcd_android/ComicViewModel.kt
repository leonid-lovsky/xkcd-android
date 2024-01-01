package com.example.xkcd_android

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
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

    private val _currentComicNumber = MediatorLiveData<Int>()
    private val _latestComicNumber = MutableLiveData<Int>()
    private val _loading = MutableLiveData<Boolean>()
    private val _message = MutableLiveData<String>()

    val currentComic = _currentComicNumber.switchMap { comicNumber ->
        getComicLiveData(comicNumber)
    }
    val latestComic = _latestComicNumber.switchMap { comicNumber ->
        getComicLiveData(comicNumber)
    }

    val loading = _loading as LiveData<Boolean>
    val message = _message as LiveData<String>

    init {
        Timber.d("${this::class.simpleName}")
        val currentComicNumber = sharedPreferences.getInt("current_comic_number", 0)
        if (currentComicNumber > 0) {
            toComic(currentComicNumber)
        } else {
            toLatestComic()
        }
    }

    fun toComic(comicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        setCurrentComicNumber(comicNumber)
        viewModelScope.launch {
            val comic = dao.getComic(comicNumber)
            Timber.d(comic.toString())
            if (comic == null) {
                fetchComic(comicNumber)
            }
        }
        _currentComicNumber.removeSource(_latestComicNumber)
    }

    fun toFirstComic() {
        Timber.i("${this::class.simpleName}")
        toComic(1)
    }

    fun toPreviousComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        toComic(currentComicNumber - 1)
    }

    fun refreshComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        viewModelScope.launch {
            fetchComic(currentComicNumber)
        }
    }

    fun toNextComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        toComic(currentComicNumber + 1)
    }

    fun toLastComic() {
        Timber.i("${this::class.simpleName}")
        val latestComicNumber = _latestComicNumber.value ?: return
        toComic(latestComicNumber)
    }

    fun toRandomComic() {
        Timber.i("${this::class.simpleName}")
        val latestComicNumber = _latestComicNumber.value ?: return
        toComic(Random.nextInt(1, latestComicNumber))
    }

    fun toLatestComic() {
        Timber.i("${this::class.simpleName}")
        viewModelScope.launch {
            fetchLatestComic()
        }
        _currentComicNumber.addSource(_latestComicNumber) { comicNumber ->
            _currentComicNumber.value = comicNumber
        }
        // To do: if no internet connection?
    }

    private suspend fun fetchComic(comicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        // To do: make it observable?
        try {
            _loading.value = true
            val response = service.getComic(comicNumber)
            Timber.d(response.toString())
            val body = response.body()
            Timber.d(body.toString())
            if (body != null) {
                setLatestComicNumber(body.num)
                dao.putComic(body)
            }
        } catch (e: Throwable) {
            Timber.e(e)
        } finally {
            _loading.value = false
        }
    }

    private suspend fun fetchLatestComic() {
        Timber.i("${this::class.simpleName}")
        // To do: make it observable?
        try {
            _loading.value = true
            val response = service.getLatestComic()
            Timber.d(response.toString())
            val body = response.body()
            Timber.d(body.toString())
            if (body != null) {
                setLatestComicNumber(body.num)
                dao.putComic(body)
            }
        } catch (e: Throwable) {
            Timber.e(e)
        } finally {
            _loading.value = false
        }
    }

    private fun getComicLiveData(number: Int): LiveData<Comic> {
        Timber.i("${this::class.simpleName}")
        return dao.getComicLiveData(number)
    }

    private fun setCurrentComicNumber(comicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        setCurrentComicNumberLiveData(comicNumber)
        setCurrentComicNumberPreferences(comicNumber)
    }

    private fun setLatestComicNumber(comicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        setLatestComicNumberLiveData(comicNumber)
        setLatestComicNumberPreferences(comicNumber)
    }

    private fun setCurrentComicNumberLiveData(comicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        if (comicNumber > 0) {
            // To do: if index out of range?
            _currentComicNumber.value = comicNumber
        }
    }

    private fun setLatestComicNumberLiveData(comicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        val latestComicNumber = _latestComicNumber.value ?: 0
        if (comicNumber > latestComicNumber) {
            // To do: if index out of range?
            _latestComicNumber.value = comicNumber
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun setCurrentComicNumberPreferences(comicNumber: Int) {
        Timber.d("${this::class.simpleName}")
        if (comicNumber > 0) {
            // To do: if index out of range?
            sharedPreferences.edit().putInt("current_comic_number", comicNumber).apply()
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun setLatestComicNumberPreferences(comicNumber: Int) {
        Timber.i("${this::class.simpleName}")
        val latestComicNumber = sharedPreferences.getInt("latest_comic_number", 0)
        if (comicNumber > latestComicNumber) {
            // To do: if index out of range?
            sharedPreferences.edit().putInt("latest_comic_number", latestComicNumber).apply()
        }
    }
}
