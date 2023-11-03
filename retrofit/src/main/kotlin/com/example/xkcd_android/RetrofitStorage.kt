package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicRemoteStorage
import com.example.xkcd_android.data.Comic

class RetrofitStorage(
    private val service: RetrofitService,
    private val converter: RetrofitConverter
) : ComicRemoteStorage {
    override fun loadLatestComic(): Comic? {
        val call = service.loadLatestComic()
        val response = call.execute()
        val data = response.body()
        if (data == null) return null
        return converter.convert(data)
    }

    override fun loadComicByNumber(number: Int): Comic? {
        val call = service.loadLatestComic()
        val response = call.execute()
        val data = response.body()
        if (data == null) return null
        return converter.convert(data)
    }
}