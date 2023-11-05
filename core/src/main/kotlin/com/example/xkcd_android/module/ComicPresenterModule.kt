package com.example.xkcd_android.module

import com.example.xkcd_android.contract.ComicPresenter

interface ComicPresenterModule {
    fun presenter(): ComicPresenter
}