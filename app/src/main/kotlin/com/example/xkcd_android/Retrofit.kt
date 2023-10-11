@file:Suppress("unused", "UnusedImport")

package com.example.xkcd_android

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class Comic(
    val month: String,
    val num: Int,
    val link: String,
    val year: String,
    val news: String,
    val safeTitle: String,
    val transcript: String,
    val alt: String,
    val img: String,
    val title: String,
    val day: String
)

interface Interface {
    @GET("info.0.json")
    fun getComic(): Call<Comic>

    @GET("{number}/info.0.json")
    fun getComic(@Path("number") number: Int): Call<Comic>
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://xkcd.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val instance: Interface = retrofit.create(Interface::class.java)

