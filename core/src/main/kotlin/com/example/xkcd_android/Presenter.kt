package com.example.xkcd_android

interface Presenter {
    fun render(state: State)
    fun render(t: Throwable)
    fun showSelectComicDialog()
}