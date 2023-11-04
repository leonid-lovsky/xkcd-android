package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicLocalStorage
import com.example.xkcd_android.data.Comic

class RoomStorage(
    private val service: RoomService,
    private val converter: RoomConverter
) : ComicLocalStorage {
    override fun loadLatestComic(): Comic? {
        val data = service.loadLatestComic()
        if (data == null) return null
        return converter.convetr(data)
    }

    override fun loadComicByNumber(number: Int): Comic? {
        val data = service.loadComicByNumber(number)
        if (data == null) return null
        return converter.convetr(data)
    }

    override fun saveComic(comic: Comic) {
        val data = converter.convert(comic)
        service.saveComic(data)
    }
}