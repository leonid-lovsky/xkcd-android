package com.example.xkcd_android

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class RetrofitInteractor : Interactor {
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(Service::class.java)

    override fun getComic(callback: Interactor.Callback) {
        service.getComic().enqueue(object : Callback<Comic> {
            override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                val comic = response.body() ?: return
                callback.onSuccess(comic.copy(link = "$baseUrl${comic.num}/"))
            }

            override fun onFailure(call: Call<Comic>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    override fun getComic(number: Int, callback: Interactor.Callback) {
        service.getComic(number).enqueue(object : Callback<Comic> {
            override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                val comic = response.body() ?: return
                callback.onSuccess(comic.copy(link = "$baseUrl${comic.num}/"))
            }

            override fun onFailure(call: Call<Comic>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    override fun cache(comic: Comic) {
        TODO("Not yet implemented")
    }

    interface Service {
        @GET("info.0.json")
        fun getComic(): Call<Comic>
        @GET("{number}/info.0.json")
        fun getComic(@Path("number") number: Int): Call<Comic>
    }

    private companion object {
        const val baseUrl = "https://xkcd.com/"
    }
}