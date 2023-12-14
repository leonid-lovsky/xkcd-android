package com.example.xkcd_android

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comics")
data class ComicEntity(
    val month: String,
    @PrimaryKey
    val num: Int,
    val link: String,
    val year: String,
    val news: String,
    @ColumnInfo(name = "safe_title")
    val safeTitle: String,
    val transcript: String,
    val alt: String,
    val img: String,
    val title: String,
    val day: String,
)
