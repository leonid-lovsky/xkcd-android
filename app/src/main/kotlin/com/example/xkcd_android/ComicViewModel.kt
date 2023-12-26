package com.example.xkcd_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val comicService: ComicService,
    private val comicDao: ComicDao,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    private val _comic = MutableLiveData<Comic>()
    private val _message = MutableLiveData<String>()
    private val _currentPage = MutableLiveData<Int>()
    private val _latestPage = MutableLiveData<Int>()

    val loading = _loading as LiveData<Boolean>
    val comic = _comic as LiveData<Comic>
    val message = _message as LiveData<String>

    fun getLatestComic() {
        viewModelScope.launch { }
    }

    fun getComic(number: Int) {
        viewModelScope.launch { }
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
