package com.example.xkcd_android

interface ComicStorageLocal {
    fun comic(number: Int): Comic
    fun save(comic: Comic)
}