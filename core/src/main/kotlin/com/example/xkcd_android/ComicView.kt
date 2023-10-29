package com.example.xkcd_android

interface ComicView {
    fun render(comicPage: ComicPage)
    fun render(error: Throwable)
    fun showComicSelectDialog()
    fun showProgress()
    fun hideProgress()
}