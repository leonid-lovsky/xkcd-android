package com.example.data

import com.example.domain.Comic

interface LocalComicStorage : ComicStorage {
    fun putComic(comic: Comic)
}