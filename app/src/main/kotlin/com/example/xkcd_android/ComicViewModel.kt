package com.example.xkcd_android

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class ComicViewModel(
    private val comicRepository: ComicRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel(), ComicContoller {

    private val _currentComic = MutableStateFlow<Comic?>(null)
    private val _latestComic = MutableStateFlow<Comic?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _error = MutableStateFlow<Throwable?>(null)

    override fun getComic(number: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _currentComic.value = comicRepository.getComic(number)
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _isLoading.value = false
            }
        }
    }

    override fun getLatestComic() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _latestComic.value = comicRepository.getLatestComic()
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _isLoading.value = false
            }
        }
    }

    override fun refreshComic() {
        // TODO: force refresh
        val currentComic = _currentComic.value ?: return
        getComic(currentComic.num)
    }

    override fun getRandomComic() {
        val latestComic = _latestComic.value ?: return
        getComic(Random.nextInt(1, latestComic.num))
    }

    override fun getFirstComic() {
        getComic(1)
    }

    override fun getLastComic() {
        val latestComic = _latestComic.value ?: return
        getComic(latestComic.num)
    }

    override fun getPreviousComic() {
        val currentComic = _currentComic.value ?: return
        getComic(currentComic.num - 1)
    }

    override fun getNextComic() {
        val currentComic = _currentComic.value ?: return
        getComic(currentComic.num + 1)
    }
}
