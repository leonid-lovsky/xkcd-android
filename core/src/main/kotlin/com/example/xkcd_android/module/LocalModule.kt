package com.example.xkcd_android.module

import com.example.xkcd_android.storage.LocalStorage

interface LocalModule {
    fun storage(): LocalStorage
}