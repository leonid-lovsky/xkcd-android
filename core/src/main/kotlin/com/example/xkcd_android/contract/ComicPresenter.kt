package com.example.xkcd_android.contract

interface ComicPresenter {
    fun setView(view: ComicView?) // TODO: finite state machine

    fun restoreState() // TODO: finite state machine
    fun saveState() // TODO: finite state machine

    fun loadLatestComic() // TODO: use-case interactor

    fun loadFirstComic() // TODO: finite state machine
    fun loadLastComic() // TODO: finite state machine
    fun loadPreviousComic() // TODO: finite state machine
    fun loadNextComic() // TODO: finite state machine

    fun refreshCurrentComic() // TODO: finite state machine

    fun displaySelectComicDialog() // TODO: ?

    fun loadComicByNumber(number: Int) // TODO: use-case interactor

    fun loadRandomComic() // // TODO: finite state machine
}