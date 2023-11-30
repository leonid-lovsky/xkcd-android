package com.example.xkcd_android

interface LocalComicStorage {
    fun getComicByNumber(number: Int): Comic?
    fun putComic(comic: Comic)
}