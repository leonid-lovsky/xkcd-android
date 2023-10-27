package com.example.xkcd_android

class ComicConverterRetrofitDefault(private val baseUrl: String) : ComicConverterRetrofit {
    override fun from(comicValueRetrofit: ComicValueRetrofit): Comic {
        return Comic(
            month = comicValueRetrofit.month,
            num = comicValueRetrofit.num,
            link = baseUrl + comicValueRetrofit.link,
            year = comicValueRetrofit.year,
            news = comicValueRetrofit.news,
            safeTitle = comicValueRetrofit.safeTitle,
            transcript = comicValueRetrofit.transcript,
            alt = comicValueRetrofit.alt,
            img = comicValueRetrofit.img,
            title = comicValueRetrofit.title,
            day = comicValueRetrofit.day
        )
    }
}