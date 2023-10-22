package com.example.xkcd_android

class ComicRepository(
    private val comicStorageLocal: ComicStorageLocal,
    private val comicStorage: ComicStorage,
) : ComicStorage {
    override fun getComic(callback: ComicCallback) {
        TODO("Not yet implemented")
    }

    override fun getComic(number: Int, callback: ComicCallback) {
        TODO("Not yet implemented")
    }
}