package com.example.xkcd_android

import android.content.SharedPreferences
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val comicService: ComicService,
    private val comicDao: ComicDao,
    private val comicSharedPreferences: SharedPreferences,
) : ViewModel() {

    private val _currentComic = MutableStateFlow<Comic?>(null)
    private val _latestComic = MutableStateFlow<Comic?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _exception = MutableStateFlow<Throwable?>(null)

    fun getComic(number: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val comic = comicService.getComic(number)
                _currentComic.value = comic
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
                val comic = comicService.getLatestComic()
                _currentComic.value = comic
            } catch (exception: Throwable) {
                _exception.value = exception
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refreshComic() {}

    fun getRandomComic() {}

    fun getFirstComic() {}

    fun getLastComic() {}

    fun getPreviousComic() {}

    fun getNextComic() {}
}
