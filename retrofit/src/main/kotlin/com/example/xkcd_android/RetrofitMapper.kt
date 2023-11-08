package com.example.xkcd_android

class RetrofitMapper(private val baseUrl: String) : Mapper<ComicData, RetrofitData> {

    override fun invoke1(value: ComicData): RetrofitData {
        throw NotImplementedError()
    }

    override fun invoke2(value: RetrofitData): ComicData {
        return ComicData(
            month = value.month,
            num = value.num,
            link = baseUrl + value.link,
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