package com.example.xkcd_android

interface ComicConverterRoom {
    fun convert(comicDataRoom: ComicDataRoom?): Comic?
    fun convert(comic: Comic?): ComicDataRoom?
}