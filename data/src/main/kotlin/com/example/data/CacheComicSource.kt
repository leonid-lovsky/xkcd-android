package com.example.data

import com.example.domain.Comic

interface CacheComicSource : ComicSource {
    fun putComic(comic: Comic)
}