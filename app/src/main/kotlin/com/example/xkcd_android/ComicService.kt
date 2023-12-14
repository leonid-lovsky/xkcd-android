package com.example.xkcd_android

import retrofit2.http.GET
import retrofit2.http.Path

interface ComicService {

    @GET("{number}/info.0.json")
    suspend fun getComic(@Path("number") number: Int): Comic

    @GET("info.0.json")
    suspend fun getLatestComic(): Comic
}
