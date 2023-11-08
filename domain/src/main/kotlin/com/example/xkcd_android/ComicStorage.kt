package com.example.xkcd_android

interface ComicStorage {
    fun loadLatestComic(): ComicData?
    fun loadComicByNumber(number: Int): ComicData?
    
    fun saveComic(comicData: ComicData)
}