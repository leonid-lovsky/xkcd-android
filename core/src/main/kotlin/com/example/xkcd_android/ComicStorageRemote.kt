package com.example.xkcd_android

interface ComicStorageRemote {
    fun comic(): Comic?
    fun comic(number: Int): Comic?
}