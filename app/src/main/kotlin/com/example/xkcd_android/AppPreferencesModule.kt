package com.example.xkcd_android

import com.example.xkcd_android.module.PreferencesModule

class AppPreferencesModule : PreferencesModule {
    private val preferences = AppPreferences()

    override fun preferences() = preferences
}