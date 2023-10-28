package com.example.xkcd_android

interface ComicView {
    fun render(comicPage: ComicPage)
    fun showComicSelectDialog()
    fun showProgress()
    fun hideProgress()
}