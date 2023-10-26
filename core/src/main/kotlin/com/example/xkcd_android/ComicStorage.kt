package com.example.xkcd_android

interface ComicStorage {
    fun comic(): Comic
    fun comic(number: Int): Comic
}