package com.example.data

import com.example.domain.Comic

interface ComicStore {
    fun getComicByNumber(number: Int): Comic
}