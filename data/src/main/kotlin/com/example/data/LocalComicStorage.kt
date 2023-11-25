package com.example.data

import com.example.domain.Comic

interface LocalComicStorage {
    fun getComicByNumber(number: Int): Comic
    fun putComic(comic: Comic)
}