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
    private val _exception = MutableStateFlow<Throwable?>(null)

    override fun getComicByNumber(number: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _currentComic.value = comicRepository.getComicByNumber(number)
            } catch (exception: Throwable) {
                _exception.value = exception
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
            } catch (exception: Throwable) {
                _exception.value = exception
            } finally {
                _isLoading.value = false
            }
        }
    }

    override fun refreshComic() {
        val currentComic = _currentComic.value ?: return
        getComicByNumber(currentComic.num)
    }

    override fun getRandomComic() {
        val latestComic = _latestComic.value ?: return
        getComicByNumber(Random.nextInt(1, latestComic.num))
    }

    override fun getFirstComic() {
        getComicByNumber(1)
    }

    override fun getLastComic() {
        val latestComic = _latestComic.value ?: return
        getComicByNumber(latestComic.num)
    }

    override fun getPreviousComic() {
        val currentComic = _currentComic.value ?: return
        getComicByNumber(currentComic.num - 1)
    }

    override fun getNextComic() {
        val currentComic = _currentComic.value ?: return
        getComicByNumber(currentComic.num + 1)
    }
}
