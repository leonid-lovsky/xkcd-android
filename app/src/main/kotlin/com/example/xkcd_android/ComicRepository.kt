package com.example.xkcd_android

interface ComicRepository {

    suspend fun getComicByNumber(number: Int): Comic
    suspend fun getLatestComic(): Comic
}
