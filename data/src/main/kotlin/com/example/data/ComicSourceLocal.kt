package com.example.data

import com.example.domain.Comic

interface ComicSourceLocal : ComicSource {
    fun putComic(comic: Comic)
}