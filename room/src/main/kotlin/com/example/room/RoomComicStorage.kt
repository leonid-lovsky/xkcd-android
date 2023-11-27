package com.example.room

import com.example.core.Logger
import com.example.data.LocalComicStorage
import com.example.data.Comic

class RoomComicStorage(
    private val roomComicService: RoomComicService,
    private val logger: Logger,
) : LocalComicStorage {
    override fun getComicByNumber(number: Int): Comic {
        val roomComic = roomComicService.getRoomComicByNumber(number)
        return roomComic.toComic()
    }

    override fun putComic(comic: Comic) {
        val roomComic = RoomComic(comic)
        roomComicService.putRoomComic(roomComic)
    }
}
