package com.example.room

import com.example.data.DBComicStore
import com.example.domain.Comic

class RoomStore(
    private val roomAPI: RoomAPI
) : DBComicStore {

    override fun getComicByNumber(number: Int): Comic {
        val roomComic = roomAPI.getRoomComicByNumber(number)
        return roomComic.toComic()
    }

    override fun putComic(comic: Comic) {
        val roomComic = RoomComic(comic)
        roomAPI.putRoomComic(roomComic)
    }
}
