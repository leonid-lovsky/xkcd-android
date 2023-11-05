package com.example.xkcd_android.module

import com.example.xkcd_android.contract.ComicLocalStorage

interface ComicLocalStorageModule {
    fun localStorage(): ComicLocalStorage
}