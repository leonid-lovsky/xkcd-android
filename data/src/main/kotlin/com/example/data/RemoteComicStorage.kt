package com.example.data

interface RemoteComicStorage : ComicStorage {
    fun getLatestComic(): Comic?
}