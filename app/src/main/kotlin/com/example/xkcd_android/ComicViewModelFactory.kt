package com.example.xkcd_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ComicViewModelFactory(private val comicRepository: ComicRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ComicViewModel(comicRepository) as T
    }
}
