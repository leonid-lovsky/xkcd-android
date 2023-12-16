package com.example.xkcd_android

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val _message = MutableStateFlow("")
    val currentComic = _currentComic.asStateFlow()
    val latestComic = _latestComic.asStateFlow()
    val isLoading = _isLoading.asStateFlow()
    val message = _message.asStateFlow()

    fun getLatestComic() {
        Log.i(ComicViewModel::class.simpleName, "getLatestComic()")
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val comic = comicService.getLatestComic()
                _currentComic.value = comic
            } catch (exception: Throwable) {
                Log.e(ComicViewModel::class.simpleName, exception.message, exception.cause)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getComic(number: Int) {
        Log.i(ComicViewModel::class.simpleName, "getComic(${number})")
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val comic = comicService.getComic(number)
                _currentComic.value = comic
            } catch (exception: Throwable) {
                Log.e(ComicViewModel::class.simpleName, exception.message, exception.cause)
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
