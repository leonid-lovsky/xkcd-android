package com.example.xkcd_android

interface ComicScreen {

    fun displayComic(comic: Comic)
    fun displaySelectComicDialog()
    fun showProgressBar()
    fun hideProgressBar()
    fun handleError(e: Throwable)
}
