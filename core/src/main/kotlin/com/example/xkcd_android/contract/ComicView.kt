package com.example.xkcd_android.contract

import com.example.xkcd_android.ComicState

interface ComicView {
    fun render(comicState: ComicState)
    fun render(error: Throwable)
    fun showComicSelectDialog()
    fun showProgress()
    fun hideProgress()
}