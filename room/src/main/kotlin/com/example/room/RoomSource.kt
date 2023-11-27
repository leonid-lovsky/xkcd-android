package com.example.room

import com.example.data.CacheComicSource
import com.example.domain.Comic

class RoomSource(
    private val roomAPI: RoomAPI
) : CacheComicSource {
    override fun getComicByNumber(number: Int): Comic {
        val roomComic = roomAPI.getRoomComicByNumber(number)
        return roomComic.toComic()
    }

    override fun putComic(comic: Comic) {
        val roomComic = RoomComic(comic)
        roomAPI.putRoomComic(roomComic)
    }
}
