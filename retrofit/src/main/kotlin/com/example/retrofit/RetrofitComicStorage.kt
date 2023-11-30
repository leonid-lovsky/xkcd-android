package com.example.retrofit

import com.example.data.RemoteComicStorage
import com.example.data.Comic

class RetrofitComicStorage(private val retrofitComicService: RetrofitComicService) :
    RemoteComicStorage {
    override fun getComicByNumber(number: Int): Comic? {
        val call = retrofitComicService.getRetrofitComicByNumber(number)
        val response = call.execute()
        val body = response.body()
        return body?.toComic()
    }

    override fun getLatestComic(): Comic? {
        val call = retrofitComicService.getLatestRetrofitComic()
        val response = call.execute()
        val body = response.body()
        return body?.toComic()
    }
}