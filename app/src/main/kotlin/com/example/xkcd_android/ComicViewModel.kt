package com.example.xkcd_android

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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
    override fun selectComic(comicNumber: Int) {
        TODO("Not yet implemented")
    }

    override fun latestComic() {
        TODO("Not yet implemented")
    }

    override fun refreshComic() {
        TODO("Not yet implemented")
    }

    override fun randomComic() {
        TODO("Not yet implemented")
    }

    override fun firstComic() {
        TODO("Not yet implemented")
    }

    override fun lastComic() {
        TODO("Not yet implemented")
    }

    override fun previousComic() {
        TODO("Not yet implemented")
    }

    override fun nextComic() {
        TODO("Not yet implemented")
    }
}

