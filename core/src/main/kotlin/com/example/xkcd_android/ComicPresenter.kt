package com.example.xkcd_android

class ComicPresenter(
    private val comicRepository: ComicRepository,
) : ComicCallback {
    private var comicUIState = ComicUIState()
    var comicView: ComicView? = null

    override fun onResponse(comic: Comic) {
        comicUIState = comicUIState.copy(comic)
        comicView?.render(comicUIState)
    }

    override fun onFailure(t: Throwable) {
        comicView?.render(t)
    }

    fun current() {
        comicStorage.getComic(this)
    }

    fun select(number: Int) {
        comicStorage.getComic(number, this)
    }

    fun first() {
        comicStorage.getComic(comicUIState.first(), this)
    }

    fun last() {
        comicStorage.getComic(comicUIState.last(), this)
    }

    fun next() {
        comicStorage.getComic(comicUIState.next(), this)
    }

    fun previous() {
        comicStorage.getComic(comicUIState.previous(), this)
    }

    fun random() {
        comicStorage.getComic(comicUIState.random(), this)
    }
}