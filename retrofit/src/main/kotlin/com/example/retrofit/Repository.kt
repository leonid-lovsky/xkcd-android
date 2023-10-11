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

        // asynchronous
        call.enqueue(object : Callback<Comic> {
            override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                // response.isSuccessful() is true if the response code is 2xx
                if (response.isSuccessful) {
                    val comic: Comic = response.body()!!
                } else {
                    val statusCode = response.code()

                    // handle request errors yourself
                    val errorBody = response.errorBody()
                }
            }

            override fun onFailure(call: Call<Comic>, t: Throwable) {
                // handle execution failures like no internet connectivity
            }
        })
    }
}