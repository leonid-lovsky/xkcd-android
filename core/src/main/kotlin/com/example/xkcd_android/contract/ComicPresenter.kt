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

    fun refreshCurrentComic()

    fun displaySelectComicDialog()
    fun loadComicByNumber(number: Int)

    fun loadRandomComic()
}