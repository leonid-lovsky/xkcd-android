package com.example.xkcd_android.contract

import com.example.xkcd_android.data.Comic

interface ComicRemoteStorage {
    fun loadLatestComic(): Comic?
    fun loadComicByNumber(number: Int): Comic?
}