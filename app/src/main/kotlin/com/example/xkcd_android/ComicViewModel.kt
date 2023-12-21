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
    private val comicDao: ComicDao,
    private val comicService: ComicService,
) : ViewModel() {

    private val _state = MutableLiveData(ComicState())
    val state get() = _state as LiveData<ComicState>

    operator fun invoke(action: ComicAction) {
        Timber.i(action.toString())
        viewModelScope.launch {
            try {
                _state.value = _state.value?.copy(loading = true)
                val comic = when (action) {
                    is ComicAction.GetComic -> _state.value?.comic
                    ComicAction.GetFirstComic -> _state.value?.comic
                    ComicAction.GetLastComic -> _state.value?.comic
                    ComicAction.GetLatestComic -> _state.value?.comic
                    ComicAction.GetNextComic -> _state.value?.comic
                    ComicAction.GetPreviousComic -> _state.value?.comic
                    ComicAction.GetRandomComic -> _state.value?.comic
                    ComicAction.RefreshComic -> _state.value?.comic
                }
                _state.value = _state.value?.copy(comic = comic)
            } catch (t: Throwable) {
                Timber.e(t)
            } finally {
                _state.value = _state.value?.copy(loading = false)
            }
        }
    }
}
