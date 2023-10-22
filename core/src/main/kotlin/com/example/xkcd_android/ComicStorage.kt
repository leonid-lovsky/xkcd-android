package com.example.xkcd_android

interface ComicStorage {
    fun getComic(callback: ComicCallback)
    fun getComic(number: Int, callback: ComicCallback)
}