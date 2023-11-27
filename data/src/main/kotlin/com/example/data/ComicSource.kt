package com.example.data

import com.example.domain.Comic

interface ComicSource {
    fun getComicByNumber(number: Int): Comic
}