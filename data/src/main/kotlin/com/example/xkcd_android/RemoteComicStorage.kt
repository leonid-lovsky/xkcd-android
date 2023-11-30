package com.example.xkcd_android

interface RemoteComicStorage {
    fun getLatestComic(): Comic?
    fun getComicByNumber(number: Int): Comic?
}