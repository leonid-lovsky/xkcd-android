package com.example.xkcd_android

import com.example.xkcd_android.data.Comic
import com.example.xkcd_android.data.Converter

class RoomConverter : Converter<Comic, RoomComic> {
    override fun convert(value: Comic): RoomComic {
        return RoomComic(
            month = value.month,
            num = value.num,
            link = value.link,
            year = value.year,
            news = value.news,
            safeTitle = value.safeTitle,
            transcript = value.transcript,
            alt = value.alt,
            img = value.img,
            title = value.title,
            day = value.day
        )
    }

    override fun convetr(value: RoomComic): Comic {
        return Comic(
            month = value.month,
            num = value.num,
            link = value.link,
            year = value.year,
            news = value.news,
            safeTitle = value.safeTitle,
            transcript = value.transcript,
            alt = value.alt,
            img = value.img,
            title = value.title,
            day = value.day
        )
    }
}