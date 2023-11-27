package com.example.data

import com.example.domain.Comic

interface RemoteComicSource : ComicSource {
    fun getLatestComic(): Comic
}