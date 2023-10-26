package com.example.xkcd_android

class ComicStorageRoom(
    private val comicDaoRoom: ComicDaoRoom,
    private val comicConverterRoom: ComicConverterRoom
) : ComicStorageLocal {
    override fun comic(number: Int): Comic {
        val comicEntityRoom = comicDaoRoom.comic(number)
        return comicConverterRoom.from(comicEntityRoom)
    }

    override fun insert(comic: Comic) {
        val comicEntityRoom = comicConverterRoom.from(comic)
        comicDaoRoom.insert(comicEntityRoom)
    }
}