package com.example.xkcd_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Integer.min
import javax.inject.Inject
import kotlin.math.max
import kotlin.random.Random

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val comicInteractor: ComicInteractor,
) : ViewModel() {

    private val _state = MutableLiveData(ComicState())
    val state get() = _state as LiveData<ComicState>

    operator fun invoke(action: ComicAction) {
        Timber.i(action.toString())
        viewModelScope.launch {
            try {
                _state.value = _state.value?.copy(loading = true)
                val comic = when (action) {
                    ComicAction.GetLatestComic -> {
                        _state.value = state.value?.copy(currentPage = 1)
                        null
                    }
                    is ComicAction.GetComic -> {
                        _state.value = state.value?.copy(currentPage = action.number)
                        null
                    }
                    ComicAction.RefreshComic -> {
                        null
                    }
                    else -> {
                        val currentPage = _state.value?.currentPage ?: 1
                        val latestPage = _state.value?.latestPage ?: currentPage
                        val desiredPage = when (action) {
                            ComicAction.GetFirstComic -> 1
                            ComicAction.GetPreviousComic -> min(currentPage - 1, 1)
                            ComicAction.GetNextComic -> max(currentPage + 1, latestPage)
                            ComicAction.GetLastComic -> latestPage
                            ComicAction.GetRandomComic -> Random.nextInt(1, latestPage)
                            else -> currentPage
                        }
                        _state.value = state.value?.copy(currentPage = desiredPage)
                        null
                    }
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
