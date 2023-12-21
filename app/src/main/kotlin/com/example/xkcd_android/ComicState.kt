package com.example.xkcd_android

data class ComicState(
    val loading: Boolean = false,
    val comic: Comic? = null,
    val message: String = "",
    val currentPage: Int = 1,
    val latestPage: Int = 1
) {

    fun copy(comic: Comic): ComicState {
        return copy(comic = comic, latestPage = comic.num)
    }
}
