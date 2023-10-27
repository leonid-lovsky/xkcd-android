package com.example.xkcd_android

class ComicConverterDefaultRoom : ComicConverterRoom {
    override fun from(comicValueRoom: ComicValueRoom): Comic {
        return Comic(
            month = comicValueRoom.month,
            num = comicValueRoom.num,
            link = comicValueRoom.link,
            year = comicValueRoom.year,
            news = comicValueRoom.news,
            safeTitle = comicValueRoom.safeTitle,
            transcript = comicValueRoom.transcript,
            alt = comicValueRoom.alt,
            img = comicValueRoom.img,
            title = comicValueRoom.title,
            day = comicValueRoom.day
        )
    }

    override fun from(comic: Comic): ComicValueRoom {
        return ComicValueRoom(
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