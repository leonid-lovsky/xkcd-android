package com.example.xkcd_android.module

import com.example.xkcd_android.contract.ComicKeyValueStore

interface ComicKeyValueStoreModule {
    fun keyValueStore(): ComicKeyValueStore
}