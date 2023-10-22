package com.example.xkcd_android

interface ComicStorageLocal : ComicStorage {
    fun save(comic: Comic?)
}