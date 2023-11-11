package com.example.xkcd_android

interface ComicPreferences {

    fun loadCurrentNumber(): Int?
    fun saveCurrentNumber(number: Int)

    fun loadLatestNumber(): Int?
    fun saveLatestNumber(number: Int)
}