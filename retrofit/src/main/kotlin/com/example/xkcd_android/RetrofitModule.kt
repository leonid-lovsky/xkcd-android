package com.example.xkcd_android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitModule {

    private val baseUrl = "https://xkcd.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService = retrofit.create(RetrofitService::class.java)
    private val retrofitConverter = RetrofitConverter(baseUrl)

    private val retrofitStorage = RetrofitStorage(retrofitService, retrofitConverter)

    fun retrofitStorage() = retrofitStorage
}