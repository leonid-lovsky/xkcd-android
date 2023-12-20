package com.example.xkcd_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
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
    private val comicInteractor: ComicInteractor,
) : ViewModel() {

    private val _currentComic = MutableLiveData<Comic>()
    private val _latestComic = MutableLiveData<Comic>()
    private val _loading = MutableLiveData<Boolean>()
    private val _message = MutableLiveData<String>()

    val comic: LiveData<Comic>
        get() = MediatorLiveData<Comic>().apply {
            addSource(_currentComic) { x ->
                value = x
            }
            addSource(_latestComic) { x ->
                value = x
            }
        }

    val loading: LiveData<Boolean> get() = _loading
    val message: LiveData<String> get() = _message

    fun getLatestComic() {
        _loading.value = true
        viewModelScope.launch {
            try {
                _latestComic.value = comicInteractor.getLatestComic()
            } catch (t: Throwable) {
                Timber.e(t)
            } finally {
                _loading.value = false
            }
        }
    }

    fun getComic(number: Int) {
        _loading.value = true
        viewModelScope.launch {
            try {
                _currentComic.value = comicInteractor.getComic(number)
            } catch (t: Throwable) {
                Timber.e(t)
            } finally {
                _loading.value = false
            }
        }
    }

    fun refreshComic() {
        val comic = comic.value ?: return
        _loading.value = true
        viewModelScope.launch {
            try {
                _currentComic.value = comicInteractor.getComic(comic.num, offlineFirst = false)
            } catch (t: Throwable) {
                Timber.e(t)
            } finally {
                _loading.value = false
            }
        }
    }

    fun getRandomComic() {
        val latestComic = _latestComic.value ?: return
        getComic(Random.nextInt(1, latestComic.num))
    }

    fun getFirstComic() {
        getComic(1)
    }

    fun getLastComic() {
        val latestComic = _latestComic.value ?: return
        getComic(latestComic.num)
    }

    fun getPreviousComic() {
        val currentComic = _currentComic.value ?: return
        getComic(currentComic.num - 1)
    }

    fun getNextComic() {
        val currentComic = _currentComic.value ?: return
        getComic(currentComic.num + 1)
    }
}
