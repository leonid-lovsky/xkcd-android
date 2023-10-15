package com.example.xkcd_android

interface Repository {
    fun getComic()
    fun getComic(number: Int)
}