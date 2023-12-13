package com.example.xkcd_android

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ComicViewModel(
    private val comicRepository: ComicRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel(), ComicContoller {

    private val _comicUIState = MutableStateFlow(ComicUIState())
    val comicUIState: StateFlow<ComicUIState> = _comicUIState.asStateFlow()
    // fun rollDice() {
    //     _comicUIState.update { currentState ->
    //         currentState.copy(
    //             firstDieValue = Random.nextInt(from = 1, until = 7),
    //             secondDieValue = Random.nextInt(from = 1, until = 7),
    //             numberOfRolls = currentState.numberOfRolls + 1,
    //         )
    //     }
    // }
    override fun getComic(number: Int) {
        _comicUIState.update { value ->
            value.copy(loading = true)
        }
        viewModelScope.launch {
            try {
                val comic = comicRepository.getComic(number)
                _comicUIState.update { value ->
                    value.copy(comic = comic)
                }
            } catch (e: Throwable) {
                _comicUIState.update { value ->
                    value.copy(error = e)
                }
            } finally {
                _comicUIState.update { value ->
                    value.copy(loading = false)
                }
            }
        }
    }

    override fun getLatestComic() {
        TODO("Not yet implemented")
    }

    override fun refreshComic() {
        TODO("Not yet implemented")
    }

    override fun getRandomComic() {
        TODO("Not yet implemented")
    }

    override fun getFirstComic() {
        TODO("Not yet implemented")
    }

    override fun getLastComic() {
        TODO("Not yet implemented")
    }

    override fun getPreviousComic() {
        TODO("Not yet implemented")
    }

    override fun getNextComic() {
        TODO("Not yet implemented")
    }
}

