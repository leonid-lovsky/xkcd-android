package com.example.xkcd_android

interface ComicStorageLocal {
    fun comic(num: Int): Comic
    fun save(comic: Comic)
}