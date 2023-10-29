package com.example.xkcd_android

interface ComicView {
    fun render(comicState: ComicState)
    fun render(error: Throwable)
    fun showComicSelectDialog()
    fun showProgress()
    fun hideProgress()
}