package com.example.xkcd_android

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComicStorageRetrofit : ComicStorage {
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val comicServiceRetrofit = retrofit.create(ComicServiceRetrofit::class.java)

    override fun getComic(callback: ComicCallback) {
        comicServiceRetrofit.getComic().enqueue(object : Callback<Comic> {
            override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                val comic = response.body()?.run {
                    copy(link = "${baseUrl}$num/")
                }
                callback.onResponse(comic)
            }

            override fun onFailure(call: Call<Comic>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    override fun getComic(number: Int, callback: ComicCallback) {
        comicServiceRetrofit.getComic(number).enqueue(object : Callback<Comic> {
            override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                val comic = response.body()?.run {
                    copy(link = "${baseUrl}$num/")
                }
                callback.onResponse(comic)
            }

            override fun onFailure(call: Call<Comic>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    private companion object {
        const val baseUrl = "https://xkcd.com/"
    }
}