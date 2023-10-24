package com.example.xkcd_android

interface ComicCallback {
    fun onResponse(comic: Comic)
    fun onFailure(t: Throwable)
}