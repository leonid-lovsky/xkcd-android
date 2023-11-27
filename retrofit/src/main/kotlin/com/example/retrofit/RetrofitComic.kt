package com.example.retrofit

import com.example.data.Comic
import com.google.gson.annotations.SerializedName

data class RetrofitComic(
    @SerializedName(value = "month") val month: String,
    @SerializedName(value = "num") val num: Int,
    @SerializedName(value = "link") val link: String,
    @SerializedName(value = "year") val year: String,
    @SerializedName(value = "news") val news: String,
    @SerializedName(value = "safe_title") val safeTitle: String,
    @SerializedName(value = "transcript") val transcript: String,
    @SerializedName(value = "alt") val alt: String,
    @SerializedName(value = "img") val img: String,
    @SerializedName(value = "title") val title: String,
    @SerializedName(value = "day") val day: String,
) {
    constructor(comic: Comic) : this(
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
        day = comic.day,
    )

    fun toComic(): Comic {
        return Comic(
            month = month,
            num = num,
            link = link,
            year = year,
            news = news,
            safeTitle = safeTitle,
            transcript = transcript,
            alt = alt,
            img = img,
            title = title,
            day = day,
        )
    }
}