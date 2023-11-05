package com.example.xkcd_android.module

import com.example.xkcd_android.contract.ComicRemoteStorage

interface ComicRemoteStorageModule {
    fun remoteStorage(): ComicRemoteStorage
}