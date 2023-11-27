package com.example.data

interface ComicStorage {
    fun getComicByNumber(number: Int): Comic?
}