package com.example.xkcd_android

import android.content.Context

class AndroidDependency(applicationContext: Context) {

    private val sharedPreferences = applicationContext
        .getSharedPreferences("Comic SharedPreferences", Context.MODE_PRIVATE)
    private val androidStateStorage = AndroidViewStateStorage(sharedPreferences)

    fun androidStateStorage() = androidStateStorage
}