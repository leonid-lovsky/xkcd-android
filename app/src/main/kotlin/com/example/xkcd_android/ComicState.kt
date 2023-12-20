package com.example.xkcd_android

data class ComicState(
    val loading: Boolean = false,
    val comic: Comic? = null,
    val message: String = "",
    val page: Int = 1,
    val latest: Int = 1,
)
