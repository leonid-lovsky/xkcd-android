package com.example.xkcd_android

import com.example.xkcd_android.function.Converter

class RoomConverter : Converter<Comic, RoomData> {
    override fun invoke(value: Comic): RoomData {
        return RoomData(
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

    override fun invoke(value: RoomData): Comic {
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