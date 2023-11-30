package com.example.xkcd_android

import android.content.Context

class AndroidModule(applicationContext: Context) {
    private val sharedPreferences =
        applicationContext.getSharedPreferences("Comic SharedPreferences", Context.MODE_PRIVATE)
    private val androidPreferences = AndroidPreferences(sharedPreferences)
    fun appPreferences() = androidPreferences
}