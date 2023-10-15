package com.example.xkcd_android

data class UIState(
    val comic: Comic? = null,
    val current: Int = 1,
    val last: Int = 1,
) {

    val range get() = first..last

    fun copy(comic: Comic): UIState {
        return copy(comic = comic, current = comic.num, last = maxOf(last, comic.num))
    }

    companion object {
        const val first = 1
    }
}