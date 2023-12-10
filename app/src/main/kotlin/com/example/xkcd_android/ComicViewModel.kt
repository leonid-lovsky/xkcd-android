package com.example.xkcd_android

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class ComicViewModel(
    private val comicRepository: ComicRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _comicUiState = MutableStateFlow(ComicUiState())
    val comicUiState: StateFlow<ComicUiState> = _comicUiState.asStateFlow()

    fun rollDice() {
        _comicUiState.update { currentState ->
            currentState.copy(
                firstDieValue = Random.nextInt(from = 1, until = 7),
                secondDieValue = Random.nextInt(from = 1, until = 7),
                numberOfRolls = currentState.numberOfRolls + 1,
            )
        }
    }

    fun selectComic(comicNumber: Int) {
        TODO("Not yet implemented")
    }

    fun latestComic() {
        TODO("Not yet implemented")
    }

    fun refreshComic() {
        TODO("Not yet implemented")
    }

    fun randomComic() {
        TODO("Not yet implemented")
    }

    fun firstComic() {
        TODO("Not yet implemented")
    }

    fun lastComic() {
        TODO("Not yet implemented")
    }

    fun previousComic() {
        TODO("Not yet implemented")
    }

    fun nextComic() {
        TODO("Not yet implemented")
    }
}

