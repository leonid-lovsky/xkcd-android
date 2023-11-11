package com.example.xkcd_android

class RoomConverter {

    fun convert(from: Comic): RoomComic {
        return RoomComic(
            month = from.month,
            num = from.num,
            link = from.link,
            year = from.year,
            news = from.news,
            safeTitle = from.safeTitle,
            transcript = from.transcript,
            alt = from.alt,
            img = from.img,
            title = from.title,
            day = from.day
        )
    }

    fun convert(from: RoomComic): Comic {
        return Comic(
            month = from.month,
            num = from.num,
            link = from.link,
            year = from.year,
            news = from.news,
            safeTitle = from.safeTitle,
            transcript = from.transcript,
            alt = from.alt,
            img = from.img,
            title = from.title,
            day = from.day
        )
    }
}