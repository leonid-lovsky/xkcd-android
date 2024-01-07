package com.example.xkcd_android

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import javax.inject.Inject

class ComicInteractor @Inject constructor(
    private val comicService: ComicService,
    private val comicDao: ComicDao,
    private val sharedPreferences: SharedPreferences,
) {

    fun getComicLiveDataByNumber(comicNumber: Int?): LiveData<Comic> {
        throw NotImplementedError()
    }

    fun refreshComic(desiredComicNumber: Int) {
        throw NotImplementedError()
    }
}