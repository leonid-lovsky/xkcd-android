package com.example.xkcd_android

interface Interactor {
    fun getComic(callback: Callback)
    fun getComic(number: Int, callback: Callback)
    fun cache(comic: Comic)

    interface Callback {
        fun onSuccess(comic: Comic?)
        fun onFailure(t: Throwable)
    }
}