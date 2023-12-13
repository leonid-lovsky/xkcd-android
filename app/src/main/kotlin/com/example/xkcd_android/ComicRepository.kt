package com.example.xkcd_android

interface ComicRepository {

    suspend fun getComic(number: Int): Comic
    suspend fun getLatestComic(): Comic
}
