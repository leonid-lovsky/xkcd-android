package com.example.xkcd_android

interface ComicContoller {

    fun loadComicByNumber(number: Int)
    fun loadLatestComic()
    fun refreshComic()
    fun loadRandomComic()
    fun loadFirstComic()
    fun loadLastComic()
    fun loadPreviousComic()
    fun loadNextComic()
}
