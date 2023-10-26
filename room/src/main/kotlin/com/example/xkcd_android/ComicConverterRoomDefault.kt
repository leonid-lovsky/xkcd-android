package com.example.xkcd_android

class ComicConverterRoomDefault : ComicConverterRoom {
    override fun from(comicEntityRoom: ComicEntityRoom): Comic {
        return Comic(
            month = comicEntityRoom.month,
            num = comicEntityRoom.num,
            link = comicEntityRoom.link,
            year = comicEntityRoom.year,
            news = comicEntityRoom.news,
            safeTitle = comicEntityRoom.safeTitle,
            transcript = comicEntityRoom.transcript,
            alt = comicEntityRoom.alt,
            img = comicEntityRoom.img,
            title = comicEntityRoom.title,
            day = comicEntityRoom.day
        )
    }

    override fun from(comic: Comic): ComicEntityRoom {
        return ComicEntityRoom(
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