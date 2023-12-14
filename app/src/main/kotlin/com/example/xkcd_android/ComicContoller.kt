package com.example.xkcd_android

interface ComicContoller {

    fun getComic(number: Int)
    fun getLatestComic()
    fun refreshComic()
    fun getRandomComic()
    fun getFirstComic()
    fun getLastComic()
    fun getPreviousComic()
    fun getNextComic()
}
