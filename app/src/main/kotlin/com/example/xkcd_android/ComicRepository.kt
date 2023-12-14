package com.example.xkcd_android

interface ComicRepository {

    suspend fun loadComicByNumber(number: Int): Comic
    suspend fun loadLatestComic(): Comic
}
