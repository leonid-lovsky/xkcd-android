package com.example.retrofit

import com.example.domain.Comic

class RetrofitStorage(
    private val retrofitService: RetrofitService,
) {

    fun loadLatestComic(): Comic? {
        val call = retrofitService.loadLatestComic()
        val response = call.execute()
        val body = response.body()
        if (body == null) return null
        return body
    }

    fun loadComicByNumber(number: Int): Comic? {
        val call = retrofitService.loadComicByNumber(number)
        val response = call.execute()
        val body = response.body()
        if (body == null) return null
        return body
    }
}