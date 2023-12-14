package com.example.xkcd_android

import com.google.gson.annotations.SerializedName

data class ComicData(
    val month: String,
    val num: Int,
    val link: String,
    val year: String,
    val news: String,
    @SerializedName("safe_title")
    val safeTitle: String,
    val transcript: String,
    val alt: String,
    val img: String,
    val title: String,
    val day: String,
)
