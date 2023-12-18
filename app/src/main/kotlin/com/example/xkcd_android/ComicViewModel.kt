package com.example.xkcd_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val comicInteractor: ComicInteractor,
) : ViewModel() {

    private val _comic = MutableLiveData<Comic>()
    private val _loading = MutableLiveData<Boolean>()
    private val _message = MutableLiveData<String>()

    val comic: LiveData<Comic> get() = _comic
    val loading: LiveData<Boolean> get() = _loading
    val message: LiveData<String> get() = _message

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

    fun refreshComic() {
        val comic = comic.value ?: return
        getComic(comic.num)
    }

    fun getRandomComic() {}

    fun getFirstComic() {}

    fun getLastComic() {}

    fun getPreviousComic() {}

    fun getNextComic() {}
}
