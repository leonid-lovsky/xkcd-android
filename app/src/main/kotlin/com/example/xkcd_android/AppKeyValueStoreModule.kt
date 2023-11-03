package com.example.xkcd_android

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.xkcd_android.module.KeyValueStoreModule

class AppKeyValueStoreModule(applicationContext: Context) : KeyValueStoreModule {
    // private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
    private val sharedPreferences = applicationContext.getSharedPreferences(
        "preferences", MODE_PRIVATE
    )
    private val keyValueStore = AppKeyValueStore(sharedPreferences)

    override fun keyValueStore() = keyValueStore
}