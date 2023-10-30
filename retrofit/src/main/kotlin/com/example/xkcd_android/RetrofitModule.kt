package com.example.xkcd_android

import com.example.xkcd_android.module.StorageModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitModule : StorageModule {
    private val baseUrl = "https://xkcd.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(RetrofitService::class.java)
    private val converter = RetrofitConverter(baseUrl)

    private val storage = RetrofitStorage(service, converter)

    override fun storage() = storage
}