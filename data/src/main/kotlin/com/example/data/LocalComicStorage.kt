package com.example.data

interface LocalComicStorage {
    fun getComicByNumber(number: Int): Comic?
    fun putComic(comic: Comic)
}