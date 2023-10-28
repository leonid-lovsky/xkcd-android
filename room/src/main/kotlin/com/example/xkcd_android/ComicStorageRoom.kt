package com.example.xkcd_android

class ComicStorageRoom(
    private val comicServiceRoom: ComicServiceRoom,
    private val comicConverterRoom: ComicConverterRoom
) : ComicStorageLocal {
    override fun comic(): Comic? {
        val comicDataRoom = comicServiceRoom.comicDataRoom()
        if (comicDataRoom == null) return null
        return comicConverterRoom.convert(comicDataRoom)
    }

    override fun comic(number: Int): Comic? {
        val comicDataRoom = comicServiceRoom.comicDataRoom(number)
        if (comicDataRoom == null) return null
        return comicConverterRoom.convert(comicDataRoom)
    }

    override fun comic(comic: Comic) {
        val comicDataRoom = comicConverterRoom.convert(comic)
        comicServiceRoom.comicDataRoom(comicDataRoom)
    }
}