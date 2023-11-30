package com.example.xkcd_android

interface ComicPreferences {
    fun getCurrentComicNumber(): Int?
    fun getLatestComicNumber(): Int?
    fun setCurrentComicNumber(number: Int)
    fun setLatestComicNumber(number: Int)
}