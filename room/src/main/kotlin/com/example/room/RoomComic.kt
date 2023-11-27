package com.example.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.data.Comic

@Entity(
    tableName = "comic", primaryKeys = ["num"]
)
data class RoomComic(
    @ColumnInfo(name = "month") val month: String,
    @ColumnInfo(name = "num") val num: Int,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "news") val news: String,
    @ColumnInfo(name = "safe_title") val safeTitle: String,
    @ColumnInfo(name = "transcript") val transcript: String,
    @ColumnInfo(name = "alt") val alt: String,
    @ColumnInfo(name = "img") val img: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "day") val day: String
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