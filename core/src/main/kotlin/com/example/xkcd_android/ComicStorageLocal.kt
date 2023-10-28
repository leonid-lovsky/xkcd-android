package com.example.xkcd_android

interface ComicStorageLocal {
    fun comic(): Comic?
    fun comic(number: Int): Comic?
    fun comic(comic: Comic?)
}