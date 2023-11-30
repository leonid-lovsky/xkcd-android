package com.example.data

interface RemoteComicStorage {
    fun getLatestComic(): Comic?
    fun getComicByNumber(number: Int): Comic?
}