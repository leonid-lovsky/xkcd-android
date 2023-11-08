package com.example.xkcd_android

class RoomDataStorage(
    private val roomService: RoomService,
    private val roomConverter: RoomConverter
) : ComicDataStorage {

    override fun loadLatestComic(): ComicData? {
        throw NotImplementedError()
    }

    override fun loadComicByNumber(number: Int): ComicData? {
        val roomData = roomService.loadComicByNumber(number)
        if (roomData == null) return null
        return roomConverter.invoke2(roomData)
    }

    override fun saveComic(comicData: ComicData) {
        val roomData = roomConverter.invoke1(comicData)
        roomService.saveComic(roomData)
    }
}