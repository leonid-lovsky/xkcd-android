package com.example.xkcd_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicServiceRetrofit {
    @GET("info.0.json")
    fun comicDataRetrofit(): Call<ComicDataRetrofit>

    @GET("{number}/info.0.json")
    fun comicDataRetrofit(@Path("number") number: Int): Call<ComicDataRetrofit>
}