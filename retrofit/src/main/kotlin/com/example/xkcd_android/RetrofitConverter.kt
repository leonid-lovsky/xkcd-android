package com.example.xkcd_android

class RetrofitConverter(private val baseUrl: String) : ComicConverter<RetrofitComic> {

    override fun convert(from: RetrofitComic): Comic {
        return Comic(
            month = from.month,
            num = from.num,
            link = baseUrl + from.link,
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