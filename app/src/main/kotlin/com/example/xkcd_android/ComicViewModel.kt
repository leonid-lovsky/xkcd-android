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

    private val _state = MutableLiveData<ComicState>()
    val state get() = _state as LiveData<ComicState>

    operator fun invoke(comicAction: ComicAction) {
        Timber.i(comicAction.toString())
        val state = _state.value ?: return
        viewModelScope.launch {
            _state.value = when (comicAction) {
                is ComicAction.GetComic -> comicInteractor.getComic(state, comicAction.number)
                ComicAction.GetLatestComic -> comicInteractor.getLatestComic(state)
                ComicAction.RefreshComic -> comicInteractor.refreshComic(state)
                ComicAction.GetRandomComic -> comicInteractor.getRandomComic(state)
                ComicAction.GetFirstComic -> comicInteractor.getFirstComic(state)
                ComicAction.GetPreviousComic -> comicInteractor.getPreviousComic(state)
                ComicAction.GetNextComic -> comicInteractor.getNextComic(state)
                ComicAction.GetLastComic -> comicInteractor.getLastComic(state)
            }
        }
    }
}
