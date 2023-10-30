package com.example.xkcd_android.module

import com.example.xkcd_android.contract.ComicPreferences

interface AppModule {
    fun comicPreferences(): ComicPreferences
}