package com.example.xkcd_android

interface ComicConverter<T> {

    fun convert(from: T): Comic

    interface Local<T> : ComicConverter<T> {

        fun convert(from: Comic): T
    }

    interface Remote<T> : ComicConverter<T>
}