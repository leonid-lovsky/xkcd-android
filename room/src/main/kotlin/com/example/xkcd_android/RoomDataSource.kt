package com.example.xkcd_android

class RoomDataSource(
    private val service: RoomService,
    private val mapper: RoomMapper
) : ComicDataSource {

    override fun loadLatestComic(): ComicData? {
        throw NotImplementedError()
    }

    override fun loadComicByNumber(number: Int): ComicData? {
        val roomData = service.loadComicByNumber(number)
        if (roomData == null) return null
        return mapper.invoke2(roomData)
    }

    override fun saveComic(comicData: ComicData) {
        val roomData = mapper.invoke1(comicData)
        service.saveComic(roomData)
    }
}