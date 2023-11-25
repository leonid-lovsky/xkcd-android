package com.example.data

import com.example.domain.Comic

interface RemoteComicStorage {
    fun getLatestComic(): Comic
    fun getComicByNumber(number: Int): Comic
}