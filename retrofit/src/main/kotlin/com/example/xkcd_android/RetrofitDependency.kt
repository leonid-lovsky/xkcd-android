package com.example.xkcd_android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitDependency {

    private val baseUrl = "https://xkcd.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(RetrofitService::class.java)
    private val mapper = RetrofitMapper(baseUrl)

    private val dataSource = RetrofitDataSource(service, mapper)

    fun dataSource() = dataSource
}