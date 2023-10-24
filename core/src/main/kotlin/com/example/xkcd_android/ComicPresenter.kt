package com.example.xkcd_android

interface ComicPresenter {
    fun render(comicState: ComicState)
    fun render(t: Throwable)
    fun showSelectComicDialog()
}