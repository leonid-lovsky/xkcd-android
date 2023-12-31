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
    private val _loading = MutableLiveData<Boolean>()
    private val _message = MutableLiveData<String>()
    private val _latestComicNumber = MutableLiveData<Int>()

    val loading = _loading as LiveData<Boolean>
    val currentComic = _currentComicNumber.switchMap { number -> getComicLiveData(number) }
    val message = _message as LiveData<String>

    init {
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
            _currentComicNumber.value = number
            // if index out of range?
        }
    }

    private fun setLatestComicNumberLiveData(number: Int) {
        Timber.d("${this::class.simpleName}")
        val latestComicNumber = _latestComicNumber.value ?: 0
        if (number > latestComicNumber) {
            _latestComicNumber.value = number
            // if index out of range?
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun setCurrentComicNumberPreferences(number: Int) {
        Timber.d("${this::class.simpleName}")
        if (number > 0) {
            sharedPreferences.edit().putInt("current_comic_number", number).apply()
            // if index out of range?
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun setLatestComicNumberPreferences(number: Int) {
        Timber.i("${this::class.simpleName}")
        val latestComicNumber = sharedPreferences.getInt("latest_comic_number", 0)
        if (number > latestComicNumber) {
            sharedPreferences.edit().putInt("latest_comic_number", latestComicNumber).apply()
            // if index out of range?
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
        viewModelScope.launch {
            val comic = dao.getComic(number)
            Timber.d(comic.toString())
            if (comic == null) {
                refreshComic(number)
            }
        }
        return dao.getComicLiveData(number)
    }

    @SuppressLint("ApplySharedPref")
    private suspend fun refreshComic(number: Int) {
        Timber.i("${this::class.simpleName}")
        // is observable?
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
    private suspend fun fetchLatestComic() {
        Timber.i("${this::class.simpleName}")
        // is observable?
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
    }

    fun toFirstComic() {
        Timber.i("${this::class.simpleName}")
        setCurrentComicNumber(1)
    }

    fun toPreviousComic() {
        Timber.i("${this::class.simpleName}")
        val currentComicNumber = _currentComicNumber.value
        if (currentComicNumber != null) {
            setCurrentComicNumber(currentComicNumber - 1)
        }
    }

    fun refreshCurrentComic() {
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
            setCurrentComicNumber(currentComicNumber + 1)
        }
    }

    fun toLastComic() {
        Timber.i("${this::class.simpleName}")
        val latestComicNumber = _latestComicNumber.value
        if (latestComicNumber != null) {
            setCurrentComicNumber(latestComicNumber)
        }
    }

    fun toRandomComic() {
        Timber.i("${this::class.simpleName}")
        val latestComicNumber = _latestComicNumber.value
        if (latestComicNumber != null) {
            setCurrentComicNumber(Random.nextInt(1, latestComicNumber))
        }
    }

    fun toLatestComic() {
        Timber.i("${this::class.simpleName}")
        viewModelScope.launch {
            fetchLatestComic()
            // Todo: get latest comic number
            // Todo: set current comic number
            // if no internet connection?
        }
    }
}
