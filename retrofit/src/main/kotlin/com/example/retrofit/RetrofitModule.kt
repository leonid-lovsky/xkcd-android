package com.example.retrofit

import com.example.core.Logger
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitModule {
    private val xkcdUrl = "https://xkcd.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(xkcdUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitComicService = retrofit.create(RetrofitComicService::class.java)

    fun retrofitComicStorage(logger: Logger): RetrofitComicStorage {
        return RetrofitComicStorage(xkcdUrl, retrofitComicService, logger)
    }
}