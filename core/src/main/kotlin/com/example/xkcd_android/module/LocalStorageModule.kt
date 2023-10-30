package com.example.xkcd_android.module

import com.example.xkcd_android.contract.ComicLocalStorage

interface LocalStorageModule {
    fun localStorage(): ComicLocalStorage
}