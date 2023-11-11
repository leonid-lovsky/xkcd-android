package com.example.xkcd_android

interface ComicView {

    fun render(comic: Comic)
    fun render(error: Throwable)
    fun render(message: String)

    fun displaySelectComicDialog()

    fun showProgress()
    fun hideProgress()
}