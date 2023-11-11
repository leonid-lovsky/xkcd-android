package com.example.xkcd_android

class RoomStorage(
    private val roomService: RoomService,
    private val roomConverter: RoomConverter
) : ComicStorage.Local {

    override fun loadComicByNumber(number: Int): Comic? {
        val roomData = roomService.loadComicByNumber(number)
        if (roomData == null) return null
        return roomConverter.convert(roomData)
    }

    override fun saveComic(comic: Comic) {
        val roomData = roomConverter.convert(comic)
        roomService.saveComic(roomData)
    }
}
