package com.example.xkcd_android

interface ComicConverterRetrofit {
    fun from(comicDataRetrofit: ComicDataRetrofit): Comic
}