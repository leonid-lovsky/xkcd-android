package com.example.xkcd_android

import android.content.SharedPreferences
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
    private val comicService: ComicService,
    private val comicDao: ComicDao,
    private val comicPreferences: SharedPreferences,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    private val _comic = MutableLiveData<Comic>()
    private val _message = MutableLiveData<String>()
    private val _currentPage = MutableLiveData<Int>()
    private val _latestPage = MutableLiveData<Int>()

    val loading = _loading as LiveData<Boolean>
    val comic = _comic as LiveData<Comic>
    val message = _message as LiveData<String>

    init {
        val currentPage_ = comicPreferences.getInt("current_page", -1)
        if (currentPage_ == -1) {
            getLatestComic()
        } else {
            getComic(currentPage_)
        }
    }

    fun getLatestComic() {
        viewModelScope.launch {
            try {
                _loading.value = true
            } catch (e: Throwable) {
                Timber.e(e)
            } finally {
                _loading.value = false
            }
        }
    }

    fun getComic(number: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true
            } catch (e: Throwable) {
                Timber.e(e)
            } finally {
                _loading.value = false
            }
        }
    }

    fun refreshComic() {
        TODO("Not yet implemented")
    }

    fun getRandomComic() {
        TODO("Not yet implemented")
    }

    fun getFirstComic() {
        TODO("Not yet implemented")
    }

    fun getLastComic() {
        TODO("Not yet implemented")
    }

    fun getPreviousComic() {
        TODO("Not yet implemented")
    }

    fun getNextComic() {
        TODO("Not yet implemented")
    }
}
