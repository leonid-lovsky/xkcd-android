package com.example.xkcd_android

class ComicController(
    private val comicStorage: ComicStorage,
) : ComicCallback {
    private var comicState = ComicState()
    var comicPresenter: ComicPresenter? = null

    override fun onResponse(comic: Comic?) {
        if (comic == null) return
        comicState = comicState.copy(comic)
        comicPresenter?.render(comicState)
    }

    override fun onFailure(t: Throwable) {
        comicPresenter?.render(t)
    }

    fun current() {
        comicStorage.getComic(this)
    }

    fun random() {
        comicStorage.getComic(comicState.range.random(), this)
    }

    fun select() {
        comicPresenter?.showSelectComicDialog()
    }

    fun select(number: Int) {
        comicStorage.getComic(number, this)
    }

    fun first() {
        comicStorage.getComic(comicState.first, this)
    }

    fun last() {
        comicStorage.getComic(comicState.last, this)
    }

    fun previous() {
        comicStorage.getComic(comicState.current - 1, this)
    }

    fun next() {
        comicStorage.getComic(comicState.current + 1, this)
    }
}