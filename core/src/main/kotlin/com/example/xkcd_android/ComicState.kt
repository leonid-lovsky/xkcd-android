package com.example.xkcd_android

data class ComicState(
    val comic: Comic? = null,
    val current: Int = 1,
    val last: Int = 1,
) {
    val first = ComicState.first
    val range get() = first..last

    fun copy(comic: Comic): ComicState {
        return copy(comic = comic, current = comic.num, last = maxOf(last, comic.num))
    }

    private companion object {
        const val first = 1
    }
}