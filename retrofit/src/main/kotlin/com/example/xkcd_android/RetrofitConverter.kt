package com.example.xkcd_android

import com.example.xkcd_android.data.Comic
import com.example.xkcd_android.function.Converter

class RetrofitConverter(private val baseUrl: String) : Converter<Comic, RetrofitData> {
    override fun invoke(value: Comic): RetrofitData {
        throw NotImplementedError()
    }

    override fun invoke(value: RetrofitData): Comic {
        return Comic(
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