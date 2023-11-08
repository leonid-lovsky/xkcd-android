package com.example.xkcd_android

interface ComicDataStorage {
    fun loadLatestComic(): ComicData?
    fun loadComicByNumber(number: Int): ComicData?
    
    fun saveComic(comicData: ComicData)
}