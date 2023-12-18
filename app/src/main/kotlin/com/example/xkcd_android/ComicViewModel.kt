package com.example.xkcd_android

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val comicInteractor: ComicInteractor,
) : ViewModel() {

    private val _comic = MutableStateFlow<Comic?>(null)
    private val _loading = MutableStateFlow(false)
    private val _message = MutableStateFlow("")

    val comic = _comic.asStateFlow()
    val loading = _loading.asStateFlow()
    val message = _message.asStateFlow()

    fun getLatestComic() {
        _loading.value = true
        viewModelScope.launch {
            try {
                _comic.value = comicInteractor.getLatestComic()
            } catch (e: Throwable) {
                Timber.e(e.cause, e.message)
            } finally {
                _loading.value = false
            }
        }
    }

    fun getComic(number: Int) {
        _loading.value = true
        viewModelScope.launch {
            try {
                _comic.value = comicInteractor.getComic(number)
            } catch (e: Throwable) {
                Timber.e(e.cause, e.message)
            } finally {
                _loading.value = false
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
