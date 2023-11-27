package com.example.data

import com.example.domain.Comic

interface ComicSourceRemote : ComicSource {
    fun getLatestComic(): Comic
}