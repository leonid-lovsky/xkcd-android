package com.example.xkcd_android

interface ComicStateStore {
    fun loadCurrentNumber(): Int?
    fun saveCurrentNumber(number: Int)

    fun loadLatestNumber(): Int?
    fun saveLatestNumber(number: Int)

    // fun loadCurrentComic(): Comic?
    // fun saveCurrentComic(comic: Comic)

    // fun loadLatestComic(): Comic?
    // fun saveLatestComic(comic: Comic)
}