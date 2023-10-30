package com.example.xkcd_android.contract

import com.example.xkcd_android.data.Comic

interface ComicStorage {
    fun comic(): Comic?
    fun comic(number: Int): Comic?
    fun comic(comic: Comic)
}