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
    private val service: ComicService,
    private val dao: ComicDao,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    private val _currentComicNumber = MutableLiveData<Int>()
    private val _latestComicNumber = MutableLiveData<Int>()

    private val _loading = MutableLiveData<Boolean>()
    private val _message = MutableLiveData<String>()

    val currentComic = _currentComicNumber.switchMap { comicNumber -> getComicLiveData(comicNumber) }
    val latestComic = _latestComicNumber.switchMap { comicNumber -> getComicLiveData(comicNumber) }

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

    private fun setCurrentComicNumberLiveData(number: Int) {
        Timber.d("${this::class.simpleName}")
        if (number > 0) {
            // To do: if index out of range?
            _currentComicNumber.value = number
        }
    }

    private fun setLatestComicNumberLiveData(number: Int) {
        Timber.d("${this::class.simpleName}")
        val latestComicNumber = _latestComicNumber.value ?: 0
        if (number > latestComicNumber) {
            // To do: if index out of range?
            _latestComicNumber.value = number
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun setCurrentComicNumberPreferences(number: Int) {
        Timber.d("${this::class.simpleName}")
        if (number > 0) {
            // To do: if index out of range?
            sharedPreferences.edit().putInt("current_comic_number", number).apply()
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun setLatestComicNumberPreferences(number: Int) {
        Timber.i("${this::class.simpleName}")
        val latestComicNumber = sharedPreferences.getInt("latest_comic_number", 0)
        if (number > latestComicNumber) {
            // To do: if index out of range?
            sharedPreferences.edit().putInt("latest_comic_number", latestComicNumber).apply()
        }
    }

    private fun setCurrentComicNumber(number: Int) {
        Timber.i("${this::class.simpleName}")
        setCurrentComicNumberLiveData(number)
        setCurrentComicNumberPreferences(number)
    }

    private fun setLatestComicNumber(number: Int) {
        Timber.i("${this::class.simpleName}")
        setLatestComicNumberLiveData(number)
        setLatestComicNumberPreferences(number)
    }

    private fun getComicLiveData(number: Int): LiveData<Comic> {
        Timber.i("${this::class.simpleName}")
        return dao.getComicLiveData(number)
    }

    @SuppressLint("ApplySharedPref")
    private suspend fun refreshComic(number: Int) {
        Timber.i("${this::class.simpleName}")
        // To do: make it observable?
        try {
            _loading.value = true
            val response = service.getComic(number)
            Timber.d(response.toString())
            val body = response.body()
            Timber.d(body.toString())
            if (body != null) {
                dao.putComic(body)
                setLatestComicNumber(body.num)
            }
        } catch (e: Throwable) {
            Timber.e(e)
        } finally {
            _loading.value = false
        }
    }

    @SuppressLint("ApplySharedPref")
    private suspend fun refreshLatestComic() {
        Timber.i("${this::class.simpleName}")
        // To do: make it observable?
        try {
            _loading.value = true
            val response = service.getLatestComic()
            Timber.d(response.toString())
            val body = response.body()
            Timber.d(body.toString())
            if (body != null) {
                dao.putComic(body)
                setLatestComicNumber(body.num)
            }
        } catch (e: Throwable) {
            Timber.e(e)
        } finally {
            _loading.value = false
        }
    }

    fun toComic(number: Int) {
        Timber.i("${this::class.simpleName}")
        setCurrentComicNumber(number)
        viewModelScope.launch {
            val comic = dao.getComic(number)
            Timber.d(comic.toString())
            if (comic == null) {
                refreshComic(number)
            }
        }
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

    fun refreshCurrentComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value ?: return
        viewModelScope.launch {
            // To do: if no internet connection?
            refreshComic(currentComicNumber)
            // Fix: set current comic number
            // Fix: set current comic
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
            // To do: if no internet connection?
            refreshLatestComic()
            // Fix: set current comic number
            // Fix: set current comic
        }
    }
}
