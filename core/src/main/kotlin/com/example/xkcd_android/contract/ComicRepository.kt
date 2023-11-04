package com.example.xkcd_android.contract

import com.example.xkcd_android.data.Callback
import com.example.xkcd_android.data.Comic
import com.example.xkcd_android.data.Resource

interface ComicRepository {
    fun loadLatestComic(callback: Resource<Comic?>)
    fun loadComicByNumber(number: Int, callback: Resource<Comic?>)

    // fun saveComic(comic: Comic)

    fun loadCurrentNumber(callback: Callback<Int?>)
    fun saveCurrentNumber(number: Int)

    fun loadLatestNumber(callback: Callback<Int?>)
    fun saveLatestNumber(number: Int)

    // fun loadCurrentComic(callback: Callback<Comic?>)
    // fun saveCurrentComic(comic: Comic)

    // fun loadLatestComic(callback: Callback<Comic?>)
    // fun saveLatestComic(comic: Comic)
}