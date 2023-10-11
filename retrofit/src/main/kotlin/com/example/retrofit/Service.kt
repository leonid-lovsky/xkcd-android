package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("info.0.json")
    fun getComic(): Call<Comic>

    @GET("{number}/info.0.json")
    fun getComic(@Path("number") number: Int): Call<Comic>
}