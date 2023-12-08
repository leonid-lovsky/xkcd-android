package com.example.xkcd_android

import androidx.lifecycle.ViewModel

class ComicViewModel(private val comicRepository: ComicRepository) : ViewModel(), ComicController {

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