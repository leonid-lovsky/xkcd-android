package com.example.xkcd_android

class RoomConverter : BaseConverter<ComicData, RoomData> {

    override fun invoke1(value: ComicData): RoomData {
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

    override fun invoke2(value: RoomData): ComicData {
        return ComicData(
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