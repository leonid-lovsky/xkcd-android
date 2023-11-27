package com.example.room

import com.example.core.Logger
import com.example.data.LocalComicStorage
import com.example.data.Comic

class RoomComicStorage(
    private val roomComicService: RoomComicService,
    private val logger: Logger,
) : LocalComicStorage {
    override fun getComicByNumber(number: Int): Comic? {
        logger.log("getComicByNumber:number:$number")
        val roomComic = roomComicService.getRoomComicByNumber(number)
        logger.log("getComicByNumber:roomComic:$roomComic")
        val result = roomComic?.toComic()
        logger.log("getComicByNumber:result:$result")
        return result
    }

    override fun putComic(comic: Comic) {
        logger.log("putComic:comic:$comic")
        val roomComic = RoomComic(comic)
        logger.log("putComic:roomComic:$roomComic")
        roomComicService.putRoomComic(roomComic)
    }
}
