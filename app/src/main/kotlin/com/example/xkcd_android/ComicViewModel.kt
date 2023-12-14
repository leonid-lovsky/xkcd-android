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

    override fun loadComicByNumber(number: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _currentComic.value = comicRepository.loadComicByNumber(number)
            } catch (exception: Throwable) {
                _exception.value = exception
            } finally {
                _isLoading.value = false
            }
        }
    }

    override fun loadLatestComic() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _latestComic.value = comicRepository.loadLatestComic()
            } catch (exception: Throwable) {
                _exception.value = exception
            } finally {
                _isLoading.value = false
            }
        }
    }

    override fun refreshComic() {
        val currentComic = _currentComic.value ?: return
        loadComicByNumber(currentComic.num)
    }

    override fun loadRandomComic() {
        val latestComic = _latestComic.value ?: return
        loadComicByNumber(Random.nextInt(1, latestComic.num))
    }

    override fun loadFirstComic() {
        loadComicByNumber(1)
    }

    override fun loadLastComic() {
        val latestComic = _latestComic.value ?: return
        loadComicByNumber(latestComic.num)
    }

    override fun loadPreviousComic() {
        val currentComic = _currentComic.value ?: return
        loadComicByNumber(currentComic.num - 1)
    }

    override fun loadNextComic() {
        val currentComic = _currentComic.value ?: return
        loadComicByNumber(currentComic.num + 1)
    }
}
