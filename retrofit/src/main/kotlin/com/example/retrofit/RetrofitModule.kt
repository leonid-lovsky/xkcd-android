package com.example.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitModule {
    private val xkcdUrl = "https://xkcd.com/"
    private val retrofit =
        Retrofit.Builder().baseUrl(xkcdUrl).addConverterFactory(GsonConverterFactory.create())
            .build()
    private val api = retrofit.create(RetrofitAPI::class.java)
    private val source = RetrofitSource(api, xkcdUrl)
    fun source() = source
}