@file:Suppress("unused", "UnusedImport") @file:JvmName("RetrofitServiceKt")

package com.example.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://xkcd.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: Service = retrofit.create(Service::class.java)

    fun getComic(number: Int?) {
        val call = if (number == null) {
            service.getComic()
        } else {
            service.getComic(number)
        }

        call.enqueue(object : Callback<Comic> {
            override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<Comic>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}