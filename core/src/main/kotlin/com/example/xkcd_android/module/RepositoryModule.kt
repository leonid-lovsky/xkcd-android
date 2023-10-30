package com.example.xkcd_android.module

import com.example.xkcd_android.contract.ComicRepository

interface RepositoryModule {
    fun repository(): ComicRepository
}