package com.example.xkcd_android

class ComicConverterRetrofitDefault(private val baseUrl: String) : ComicConverterRetrofit {
    override fun convert(comicDataRetrofit: ComicDataRetrofit): Comic {
        return Comic(
            month = comicDataRetrofit.month,
            num = comicDataRetrofit.num,
            link = baseUrl + comicDataRetrofit.link,
            year = comicDataRetrofit.year,
            news = comicDataRetrofit.news,
            safeTitle = comicDataRetrofit.safeTitle,
            transcript = comicDataRetrofit.transcript,
            alt = comicDataRetrofit.alt,
            img = comicDataRetrofit.img,
            title = comicDataRetrofit.title,
            day = comicDataRetrofit.day
        )
    }
}