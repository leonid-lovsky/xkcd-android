package com.example.xkcd_android

interface ComicConverterRoom {
    fun from(comicEntityRoom: ComicEntityRoom): Comic
    fun from(comic: Comic): ComicEntityRoom
}