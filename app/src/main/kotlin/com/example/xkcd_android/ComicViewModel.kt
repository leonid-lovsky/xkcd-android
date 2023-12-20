package com.example.xkcd_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
        _state.value = reduce(_state.value!!, action)
    }

    private fun reduce(state: ComicState, action: ComicAction): ComicState {
        Timber.i(state.toString(), action.toString())
        return when (action) {
            is ComicAction.GetComic -> state
            ComicAction.GetFirstComic -> state
            ComicAction.GetLastComic -> state
            ComicAction.GetLatestComic -> state
            ComicAction.GetNextComic -> state
            ComicAction.GetPreviousComic -> state
            ComicAction.GetRandomComic -> state
            ComicAction.RefreshComic -> state
        }
    }
}
