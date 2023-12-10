package com.example.xkcd_android

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras

class ComicViewModel(
    private val comicRepository: ComicRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

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

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                val savedStateHandle = extras.createSavedStateHandle()

                return ComicViewModel(
                    (application as ComicApplication).comicRepository,
                    savedStateHandle
                ) as T
            }
        }
    }
}