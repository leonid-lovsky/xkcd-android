package com.example.xkcd_android.contract

interface ComicPresenter {
    fun setView(view: ComicView?)

    fun restoreState()
    fun saveState()

    fun latest()

    fun first()
    fun last()
    fun previous()
    fun next()

    fun refresh()
    fun select()
    fun select(number: Int)
    fun random()
}