package com.example.xkcd_android

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val comicService: ComicService,
) : ViewModel() {

    private val _currentComic = MutableStateFlow<Comic?>(null)
    private val _latestComic = MutableStateFlow<Comic?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _exception = MutableStateFlow<Throwable?>(null)

    fun getComic(number: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _currentComic.value = comicService.getComic(number)
            } catch (exception: Throwable) {
                _exception.value = exception
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getLatestComic() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _latestComic.value = comicService.getLatestComic()
            } catch (exception: Throwable) {
                _exception.value = exception
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refreshComic() {
        val currentComic = _currentComic.value ?: return
        getComic(currentComic.num)
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
