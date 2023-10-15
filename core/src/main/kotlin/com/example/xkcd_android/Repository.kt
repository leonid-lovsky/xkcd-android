package com.example.xkcd_android

interface Repository {
    fun getComic(callback: Callback)
    fun getComic(number: Int, callback: Callback)

    interface Callback {
        fun onSuccess(comic: Comic?)
        fun onFailure(t: Throwable)
    }
}