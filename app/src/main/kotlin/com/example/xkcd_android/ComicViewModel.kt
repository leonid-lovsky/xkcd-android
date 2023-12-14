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
    private val comicRepository: ComicRepository,
) : ViewModel(), ComicContoller {

    private val _currentComic = MutableStateFlow<Comic?>(null)
    private val _latestComic = MutableStateFlow<Comic?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _exception = MutableStateFlow<Throwable?>(null)

    override fun getComic(number: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _currentComic.value = comicRepository.getComic(number)
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
