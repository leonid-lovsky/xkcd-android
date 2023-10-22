package com.example.xkcd_android

class ComicUIController(
    private val comicStorage: ComicStorage,
) : ComicCallback {
    private var comicUIState = ComicUIState()
    var comicUIPresenter: ComicUIPresenter? = null

    override fun onResponse(comic: Comic?) {
        if (comic == null) return
        comicUIState = comicUIState.copy(comic)
        comicUIPresenter?.render(comicUIState)
    }

    override fun onFailure(t: Throwable) {
        comicUIPresenter?.render(t)
    }

    fun current() {
        comicStorage.getComic(this)
    }

    fun random() {
        comicStorage.getComic(comicUIState.range.random(), this)
    }

    fun select() {
        comicUIPresenter?.showSelectComicDialog()
    }

    fun select(number: Int) {
        comicStorage.getComic(number, this)
    }

    fun first() {
        comicStorage.getComic(comicUIState.first, this)
    }

    fun last() {
        comicStorage.getComic(comicUIState.last, this)
    }

    fun previous() {
        comicStorage.getComic(comicUIState.current - 1, this)
    }

    fun next() {
        comicStorage.getComic(comicUIState.current + 1, this)
    }
}