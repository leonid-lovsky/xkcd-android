@file:Suppress("unused", "UnusedImport")

package com.example.xkcd_android

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path


object Retrofit {
    interface Client {
        @GET("https://xkcd.com/info.0.json")
        fun getComic(): Callback<Comic>

        @GET("https://xkcd.com/{number}/info.0.json")
        fun getComic(@Path("number") number: Int): Callback<Comic>
    }

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
}