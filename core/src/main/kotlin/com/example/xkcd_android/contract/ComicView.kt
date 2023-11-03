package com.example.xkcd_android.contract

import com.example.xkcd_android.data.Comic

interface ComicView {
    fun renderComic(comic: Comic)
    fun renderError(error: Throwable)

    fun showSelectComicDialog()

    fun showProgress()
    fun hideProgress()
}