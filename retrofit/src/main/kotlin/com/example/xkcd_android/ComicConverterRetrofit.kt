package com.example.xkcd_android

interface ComicConverterRetrofit {
    fun convert(comicDataRetrofit: ComicDataRetrofit?): Comic?
}