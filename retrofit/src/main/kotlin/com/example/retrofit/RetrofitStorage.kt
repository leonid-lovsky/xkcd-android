package com.example.retrofit

import com.example.data.RemoteComicStorage
import com.example.domain.Comic

class RetrofitStorage(
    private val retrofitService: RetrofitService,
    private val baseUrl: String,
) : RemoteComicStorage {

    override fun getLatestComic(): Comic {
        val call = retrofitService.getLatestRetrofitComic()
        val response = call.execute()
        val body = response.body()
        if (body == null) throw NullPointerException()
        return body.copy(link = baseUrl + body.link).toComic()
    }

    override fun getComicByNumber(number: Int): Comic {
        val call = retrofitService.getRetrofitComicByNumber(number)
        val response = call.execute()
        val body = response.body()
        if (body == null) throw NullPointerException()
        return body.copy(link = baseUrl + body.link).toComic()
    }
}