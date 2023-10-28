package com.example.xkcd_android

class ComicPresenter(private val comicRepository: ComicRepository) : ComicCallback {
    private var comicUIState = ComicUIState()
    var comicView: ComicView? = null

    override fun onChanged(comic: Comic) {
        comicUIState = comicUIState.copy(comic)
        comicView?.render(comicUIState)
    }

    fun current() {
        comicRepository.comic(this)
    }

    fun select(number: Int) {
        comicRepository.comic(number, this)
    }

    fun first() {
        comicRepository.comic(comicUIState.first(), this)
    }

    fun last() {
        comicRepository.comic(comicUIState.last(), this)
    }

    fun previous() {
        comicRepository.comic(comicUIState.previous(), this)
    }

    fun next() {
        comicRepository.comic(comicUIState.next(), this)
    }

    fun refresh() {
        comicRepository.comic(comicUIState.current(), this)
    }

    fun random() {
        comicRepository.comic(comicUIState.random(), this)
    }
}