package com.example.retrofit

import com.example.core.Callback
import com.example.core.Comic
import com.example.core.Repository
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryImpl(callback: Callback<Comic?>) : Repository {
    private val retrofit = Retrofit.Builder().baseUrl("https://xkcd.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val service = retrofit.create(Service::class.java)

    private val callback = object : retrofit2.Callback<Comic> {
        override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
            callback.onSuccess(response.body(), "Response: $response")
        }

        override fun onFailure(call: Call<Comic>, t: Throwable) {
            callback.onFailure(t)
        }
    }

    override fun requestComic() {
        service.getComic().enqueue(callback)
    }

    override fun requestComic(number: Int) {
        service.getComic(number).enqueue(callback)
    }
}