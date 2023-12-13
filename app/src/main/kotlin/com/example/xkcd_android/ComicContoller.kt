package com.example.xkcd_android

interface ComicContoller {

    fun selectComic(number: Int)
    fun latestComic()
    fun refreshComic()
    fun randomComic()
    fun firstComic()
    fun lastComic()
    fun previousComic()
    fun nextComic()
}
