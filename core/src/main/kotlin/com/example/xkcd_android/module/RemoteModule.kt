package com.example.xkcd_android.module

import com.example.xkcd_android.storage.RemoteStorage

interface RemoteModule {
    fun storage(): RemoteStorage
}