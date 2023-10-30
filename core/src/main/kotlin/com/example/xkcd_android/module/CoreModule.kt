package com.example.xkcd_android.module

import com.example.xkcd_android.contract.ComicPresenter

interface CoreModule {
    fun presenter(): ComicPresenter
}