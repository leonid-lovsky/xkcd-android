package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicStorage
import com.example.xkcd_android.data.Comic

class RoomStorage(
    private val service: RoomService,
    private val converter: RoomConverter
) : ComicStorage {
    override fun comic(): Comic? {
        val data = service.invoke()
        if (data == null) return null
        return converter.invoke(data)
    }

    override fun comic(number: Int): Comic? {
        val data = service.invoke(number)
        if (data == null) return null
        return converter.invoke(data)
    }

    override fun comic(comic: Comic) {
        val data = converter.invoke(comic)
        service.invoke(data)
    }
}