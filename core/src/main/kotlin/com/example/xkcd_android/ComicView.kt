package com.example.xkcd_android

interface ComicView {
    fun render(comicUIState: ComicUIState)
    fun render(t: Throwable)
}