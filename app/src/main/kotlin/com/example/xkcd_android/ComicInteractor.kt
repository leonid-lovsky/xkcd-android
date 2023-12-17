package com.example.xkcd_android

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class ComicInteractor @Inject constructor(
    private val comicService: ComicService,
    private val comicDao: ComicDao,
    private val comicSharedPreferences: SharedPreferences,
) {

    fun getLatestComic(): Comic? {
        Log.i(ComicViewModel::class.simpleName, "getLatestComic()")
        return null
    }

    fun getComic(number: Int): Comic? {
        Log.i(ComicViewModel::class.simpleName, "getLatestComic()")
        return null
    }
}