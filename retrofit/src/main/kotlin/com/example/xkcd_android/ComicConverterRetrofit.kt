package com.example.xkcd_android

interface ComicConverterRetrofit {
    fun from(comicValueRetrofit: ComicValueRetrofit): Comic
}