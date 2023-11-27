package com.example.data

import com.example.domain.Comic

interface ComicStorage {
    fun getComicByNumber(number: Int): Comic
}