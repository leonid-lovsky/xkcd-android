package com.example.xkcd_android

interface ComicStorage {

    fun loadComicByNumber(number: Int): Comic?

    interface Local : ComicStorage {

        fun saveComic(comic: Comic)
    }

    interface Remote : ComicStorage {

        fun loadLatestComic(): Comic?
    }
}