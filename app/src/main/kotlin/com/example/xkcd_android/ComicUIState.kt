package com.example.xkcd_android

data class ComicUIState(
    val comic: Comic? = null,
    val loading: Boolean = false,
    val error: Throwable? = null,
)
