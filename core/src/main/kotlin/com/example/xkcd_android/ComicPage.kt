package com.example.xkcd_android

data class ComicPage(
    val comic: Comic? = null,
    val current: Int = 1,
    val last: Int = 1,
) {
    val first = ComicPage.first

    fun copy(comic: Comic): ComicPage {
        return copy(comic = comic, current = comic.num, last = maxOf(last, comic.num))
    }

    companion object {
        private const val first = 1
    }
}