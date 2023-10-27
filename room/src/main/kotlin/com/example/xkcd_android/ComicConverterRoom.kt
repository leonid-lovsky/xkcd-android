package com.example.xkcd_android

interface ComicConverterRoom {
    fun from(comicValueRoom: ComicValueRoom): Comic
    fun from(comic: Comic): ComicValueRoom
}