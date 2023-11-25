package com.example.room

import com.example.data.LocalComicStorage
import com.example.domain.Comic

class RoomStorage(
    private val roomService: RoomService
) : LocalComicStorage {

    override fun getComicByNumber(number: Int): Comic {
        val roomComic = roomService.getRoomComicByNumber(number)
        return roomComic.toComic()
    }

    override fun putComic(comic: Comic) {
        val roomComic = RoomComic(comic)
        roomService.putRoomComic(roomComic)
    }
}
