package com.example.xkcd_android.contract

interface ComicPreferences {
    fun current(): Int?
    fun current(number: Int)
    fun latest(): Int?
    fun latest(number: Int)
}