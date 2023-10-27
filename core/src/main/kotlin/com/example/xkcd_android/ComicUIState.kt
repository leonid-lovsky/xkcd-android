package com.example.xkcd_android

import kotlin.random.Random

data class ComicUIState(
    val comic: Comic,
    val current: Int,
    val last: Int,
) {

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

    fun copy(comic: Comic): ComicUIState {
        return copy(comic = comic, current = comic.num, last = maxOf(last, comic.num))
    }

    companion object {
        private const val first = 1
    }
}