package com.example.xkcd_android

interface ComicCallback {
    fun onSuccess(comic: Comic?)
    fun onFailure(t: Throwable)
}