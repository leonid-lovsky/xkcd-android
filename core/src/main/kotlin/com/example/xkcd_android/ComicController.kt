package com.example.xkcd_android

class ComicController(
    private val comicStorage: ComicStorage,
) : ComicCallback {
    private var comicState = ComicState()
    var comicPresenter: ComicPresenter? = null

    override fun onResponse(comic: Comic) {
        comicState = comicState.copy(comic)
        comicPresenter?.render(comicState)
    }

    override fun onFailure(t: Throwable) {
        comicPresenter?.render(t)
    }

    fun current() {
        comicStorage.getComic(this)
    }

    fun select(number: Int) {
        comicStorage.getComic(number, this)
    }

    fun first() {
        comicStorage.getComic(comicState.first(), this)
    }

    fun last() {
        comicStorage.getComic(comicState.last(), this)
    }

    fun next() {
        comicStorage.getComic(comicState.next(), this)
    }

    fun previous() {
        comicStorage.getComic(comicState.previous(), this)
    }

    fun random() {
        comicStorage.getComic(comicState.random(), this)
    }
}