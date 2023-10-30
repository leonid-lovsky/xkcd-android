package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicStorage
import com.example.xkcd_android.data.Comic

class RetrofitStorage(
    private val service: RetrofitService,
    private val converter: RetrofitConverter
) : ComicStorage {
    override fun comic(): Comic? {
        val call = service.invoke()
        val response = call.execute()
        val data = response.body()
        if (data == null) return null
        return converter.invoke(data)
    }

    override fun comic(number: Int): Comic? {
        val call = service.invoke()
        val response = call.execute()
        val data = response.body()
        if (data == null) return null
        return converter.invoke(data)
    }

    override fun comic(comic: Comic) {
        throw NotImplementedError()
    }
}