package com.example.xkcd_android

interface ComicPresenter {
    fun render(comicUIState: ComicUIState)
    fun render(t: Throwable)
    fun showSelectComicDialog()
}