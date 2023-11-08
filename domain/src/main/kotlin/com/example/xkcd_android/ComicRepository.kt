package com.example.xkcd_android

interface ComicRepository {
    fun loadLatestComic(callback: Callback<Resource<ComicData>>)
    fun loadComicByNumber(number: Int, callback: Callback<Resource<ComicData>>)

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