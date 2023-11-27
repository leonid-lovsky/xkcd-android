package com.example.data

import com.example.domain.Comic

interface RemoteComicStorage : ComicStorage {
    fun getLatestComic(): Comic
}