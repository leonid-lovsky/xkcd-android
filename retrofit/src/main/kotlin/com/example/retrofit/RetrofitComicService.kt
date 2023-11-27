package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitComicService {
    @GET("info.0.json")
    fun getLatestRetrofitComic(): Call<RetrofitComic>

    @GET("{number}/info.0.json")
    fun getRetrofitComicByNumber(@Path("number") number: Int): Call<RetrofitComic>
}