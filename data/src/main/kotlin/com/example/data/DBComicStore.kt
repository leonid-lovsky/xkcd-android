package com.example.data

import com.example.domain.Comic

interface DBComicStore : ComicStore {
    fun putComic(comic: Comic)
}