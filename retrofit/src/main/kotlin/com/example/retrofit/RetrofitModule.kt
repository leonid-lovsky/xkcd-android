package com.example.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitModule {

    private val baseUrl = "https://xkcd.com/"

    private val retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()

    private val retrofitAPI = retrofit.create(RetrofitAPI::class.java)
    private val retrofitStorage = RetrofitStore(retrofitAPI, baseUrl)

    fun retrofitStorage() = retrofitStorage
}