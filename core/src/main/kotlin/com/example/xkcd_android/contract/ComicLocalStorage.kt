package com.example.xkcd_android.contract

import com.example.xkcd_android.data.Comic

interface ComicLocalStorage {
    fun loadLatestComic(): Comic?
    fun loadComicByNumber(number: Int): Comic?
    
    fun saveComic(comic: Comic)
}