package com.example.xkcd_android

import kotlin.random.Random

data class ComicUIState(
    val comic: Comic? = null,
    val current: Int = 1,
    val last: Int = 1,
) {

    fun copy(comic: Comic): ComicUIState {
        return copy(comic = comic, current = comic.num, last = maxOf(last, comic.num))
    }

    fun current(): Int {
        return current
    }

    fun first(): Int {
        return first
    }

    fun last(): Int {
        return last
    }

    fun previous(): Int {
        return current - 1
    }

    fun next(): Int {
        return current + 1
    }

    fun random(): Int {
        return Random.nextInt(first, last)
    }

    companion object {
        private const val first = 1
    }
}