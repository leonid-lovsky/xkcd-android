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
    private val getComic: (number: Int) -> Comic,
    private val getLatestComic: () -> Comic,
) : ViewModel() {

    private val _state = MutableLiveData<ComicState>()
    val state get() = _state as LiveData<ComicState>

    operator fun invoke(action: ComicAction) {
        Timber.i(action.toString())
        _state.value = _state.value?.copy(loading = true)
        viewModelScope.launch {
            _state.value?.copy(comic = when (action) {
                is ComicAction.GetComic -> getComic(action.number)
                ComicAction.GetLatestComic -> getLatestComic()
                ComicAction.RefreshComic -> {
                    getComic(refresh = True)
                }
                ComicAction.GetRandomComic -> comicInteractor.getRandomComic(state)
                ComicAction.GetFirstComic -> comicInteractor.getFirstComic(state)
                ComicAction.GetPreviousComic -> comicInteractor.getPreviousComic(state)
                ComicAction.GetNextComic -> comicInteractor.getNextComic(state)
                ComicAction.GetLastComic -> comicInteractor.getLastComic(state)
            })
        }
    }
}
