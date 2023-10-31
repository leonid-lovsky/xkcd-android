package com.example.xkcd_android.module

import com.example.xkcd_android.contract.ComicKeyValueStore

interface KeyValueStoreModule {
    fun keyValueStore(): ComicKeyValueStore
}