package com.example.xkcd_android.contract

import com.example.xkcd_android.function.Callback
import com.example.xkcd_android.ComicState

interface ComicPreferences {
    fun saveState(state: ComicState)
    fun loadState(callback: Callback<ComicState?>)
}