package com.example.xkcd_android

import android.content.Context

class AndroidDependency(applicationContext: Context) {

    private val sharedPreferences = applicationContext.getSharedPreferences(
        "Comic SharedPreferences", Context.MODE_PRIVATE
    )
    private val stateStore = AndroidStateStorage(sharedPreferences)

    fun stateStore() = stateStore
}