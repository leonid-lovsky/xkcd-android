package com.example.xkcd_android

interface ComicUIPresenter {
    fun render(comicUIState: ComicUIState)
    fun render(t: Throwable)
    fun showSelectComicDialog()
}