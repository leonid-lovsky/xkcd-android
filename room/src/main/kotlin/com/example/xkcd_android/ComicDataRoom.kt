package com.example.xkcd_android

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comics")
data class ComicDataRoom(
    @ColumnInfo(name = "month")
    val month: String,
    @PrimaryKey
    @ColumnInfo(name = "num")
    val num: Int,
    @ColumnInfo(name = "link")
    val link: String,
    @ColumnInfo(name = "year")
    val year: String,
    @ColumnInfo(name = "news")
    val news: String,
    @ColumnInfo(name = "safe_title")
    val safeTitle: String,
    @ColumnInfo(name = "transcript")
    val transcript: String,
    @ColumnInfo(name = "alt")
    val alt: String,
    @ColumnInfo(name = "img")
    val img: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "day")
    val day: String
)