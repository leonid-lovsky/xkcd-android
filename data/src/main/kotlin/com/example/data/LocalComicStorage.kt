package com.example.data

interface LocalComicStorage : ComicStorage {
    fun putComic(comic: Comic)
}