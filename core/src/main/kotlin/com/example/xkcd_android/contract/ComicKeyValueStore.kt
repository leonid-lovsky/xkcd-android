package com.example.xkcd_android.contract

import com.example.xkcd_android.data.Comic

interface ComicKeyValueStore {
    fun loadCurrentNumber(): Int?
    fun saveCurrentNumber(number: Int)

    fun loadLatestNumber(): Int?
    fun saveLatestNumber(number: Int)

    // fun loadCurrentComic(): Comic?
    // fun saveCurrentComic(comic: Comic)

    // fun loadLatestComic(): Comic?
    // fun saveLatestComic(comic: Comic)
}