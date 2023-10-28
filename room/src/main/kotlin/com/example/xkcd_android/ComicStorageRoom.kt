package com.example.xkcd_android

class ComicStorageRoom(
    private val comicServiceRoom: ComicServiceRoom,
    private val comicConverterRoom: ComicConverterRoom
) : ComicStorageLocal {
    override fun comic(): Comic? {
        val comicDataRoom = comicServiceRoom.comicDataRoom()
        return comicConverterRoom.convert(comicDataRoom)
    }

    override fun comic(number: Int): Comic? {
        val comicDataRoom = comicServiceRoom.comicDataRoom(number)
        return comicConverterRoom.convert(comicDataRoom)
    }

    override fun comic(comic: Comic?) {
        val comicDataRoom = comicConverterRoom.convert(comic)
        if (comicDataRoom == null) return
        comicServiceRoom.comicDataRoom(comicDataRoom)
    }
}