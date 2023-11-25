package com.example.room

import com.example.domain.Comic

class RoomStorage(
    private val roomService: RoomService,
) {

    fun loadComicByNumber(number: Int): Comic? {
        val roomData = roomService.loadComicByNumber(number)
        if (roomData == null) return null
        return roomData
    }

    fun saveComic(comic: Comic) {
        roomService.saveComic(comic)
    }
}
