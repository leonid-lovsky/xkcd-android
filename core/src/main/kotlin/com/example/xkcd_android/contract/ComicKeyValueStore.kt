package com.example.xkcd_android.contract

interface ComicKeyValueStore {
    fun current(): Int?
    fun current(number: Int)
    fun latest(): Int?
    fun latest(number: Int)
}