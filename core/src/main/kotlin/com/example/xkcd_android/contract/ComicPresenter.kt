package com.example.xkcd_android.contract

interface ComicPresenter {
    fun setView(view: ComicView?)

    fun restoreState()
    fun saveState()

    fun loadLatestComic()

    fun loadFirstComic()
    fun loadLastComic()
    fun loadPreviousComic()
    fun loadNextComic()

    fun loadCurrentComic()

    fun loadComicByNumber()
    fun loadComicByNumber(number: Int)

    fun loadRandomComic()
}