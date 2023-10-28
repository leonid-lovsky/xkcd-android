package com.example.xkcd_android

class ComicConverterRoomDefault : ComicConverterRoom {
    override fun convert(comicDataRoom: ComicDataRoom): Comic {
        return Comic(
            month = comicDataRoom.month,
            num = comicDataRoom.num,
            link = comicDataRoom.link,
            year = comicDataRoom.year,
            news = comicDataRoom.news,
            safeTitle = comicDataRoom.safeTitle,
            transcript = comicDataRoom.transcript,
            alt = comicDataRoom.alt,
            img = comicDataRoom.img,
            title = comicDataRoom.title,
            day = comicDataRoom.day
        )
    }

    override fun convert(comic: Comic): ComicDataRoom {
        return ComicDataRoom(
            month = comic.month,
            num = comic.num,
            link = comic.link,
            year = comic.year,
            news = comic.news,
            safeTitle = comic.safeTitle,
            transcript = comic.transcript,
            alt = comic.alt,
            img = comic.img,
            title = comic.title,
            day = comic.day
        )
    }
}