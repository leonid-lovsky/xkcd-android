package com.example.xkcd_android

interface ComicDataSource {
    fun loadLatestComic(): ComicData?
    fun loadComicByNumber(number: Int): ComicData?
    
    fun saveComic(comicData: ComicData)
}