package com.example.data

import com.example.domain.Comic

interface WebComicStore : ComicStore {
    fun getLatestComic(): Comic
}