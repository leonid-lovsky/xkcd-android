package com.example.xkcd_android.module

import com.example.xkcd_android.contract.ComicStorage

interface StorageModule {
    fun storage(): ComicStorage
}