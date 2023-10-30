package com.example.xkcd_android.module

import com.example.xkcd_android.contract.ComicRemoteStorage

interface RemoteStorageModule {
    fun localStorage(): ComicRemoteStorage
}