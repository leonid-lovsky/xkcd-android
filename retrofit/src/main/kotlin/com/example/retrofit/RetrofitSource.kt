package com.example.retrofit

import com.example.data.ComicSourceRemote
import com.example.domain.Comic

class RetrofitSource(
    private val retrofitAPI: RetrofitAPI,
    private val baseUrl: String,
) : ComicSourceRemote {
    override fun getLatestComic(): Comic {
        val call = retrofitAPI.getLatestRetrofitComic()
        val response = call.execute()
        val body = response.body()
        if (body == null) throw NullPointerException()
        return body.copy(link = baseUrl + body.link).toComic()
    }

    override fun getComicByNumber(number: Int): Comic {
        val call = retrofitAPI.getRetrofitComicByNumber(number)
        val response = call.execute()
        val body = response.body()
        if (body == null) throw NullPointerException()
        return body.copy(link = baseUrl + body.link).toComic()
    }
}