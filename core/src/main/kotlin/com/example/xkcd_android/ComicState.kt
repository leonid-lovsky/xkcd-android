package com.example.xkcd_android

import kotlin.random.Random

data class ComicState(
    val comic: Comic,
    val current: Int,
    val last: Int,
) {
    private val first = ComicState.first

    fun first(): Int {
        return first
    }

    fun last(): Int {
        return last
    }

    fun next(): Int {
        return current + 1
    }

    fun previous(): Int {
        return current - 1
    }

    fun random(): Int {
        return Random.nextInt(first, last)
    }

    fun copy(comic: Comic): ComicState {
        return copy(comic = comic, current = comic.num, last = maxOf(last, comic.num))
    }

    companion object {
        private const val first = 1
    }
}