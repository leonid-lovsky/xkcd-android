package com.example.xkcd_android

interface Presenter {
    fun render(comic: Comic?)
    fun render(comic: Throwable)
}