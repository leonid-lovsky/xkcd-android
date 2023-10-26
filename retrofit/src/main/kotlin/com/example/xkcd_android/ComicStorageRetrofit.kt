package com.example.xkcd_android

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComicStorageRetrofit(private val comicServiceRetrofit: ComicServiceRetrofit) :
    ComicStorageRemote {

    override fun getComic(callback: ComicCallback) {
        comicServiceRetrofit.comic().enqueue(object : Callback<Comic> {
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
        comicServiceRetrofit.comic(number).enqueue(object : Callback<Comic> {
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
}