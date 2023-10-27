package com.example.xkcd_android

class ComicStorageRoom(
    private val comicServiceRoom: ComicServiceRoom,
    private val comicConverterRoom: ComicConverterRoom
) : ComicStorageLocal {
    override fun comic(number: Int): Comic {
        val comicEntityRoom = comicServiceRoom.comic(number)
        return comicConverterRoom.from(comicEntityRoom)
    }

    override fun comic(comic: Comic) {
        val comicEntityRoom = comicConverterRoom.from(comic)
        comicServiceRoom.comic(comicEntityRoom)
    }
}