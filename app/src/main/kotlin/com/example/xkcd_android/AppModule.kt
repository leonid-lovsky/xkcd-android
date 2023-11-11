package com.example.xkcd_android

import android.content.Context

class AppModule(applicationContext: Context) {

    private val sharedPreferences = applicationContext
        .getSharedPreferences("Comic SharedPreferences", Context.MODE_PRIVATE)
    private val appPreferences = AppPreferences(sharedPreferences)

    fun appPreferences() = appPreferences
}