package com.example.xkcd_android

data class ComicState(
    val comic: Comic,
    val current: Int,
    val last: Int,
) {
    val first = ComicState.first
    val next = current + 1
    val previous = current - 1
    val range = first..last

    fun copy(comic: Comic): ComicState {
        return copy(comic = comic, current = comic.num, last = maxOf(last, comic.num))
    }

    companion object {
        private const val first = 1
    }
}