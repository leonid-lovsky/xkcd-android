package com.example.xkcd_android

import android.content.SharedPreferences
import com.example.xkcd_android.contract.ComicKeyValueStore

class AppKeyValueStore(private val sharedPreferences: SharedPreferences) : ComicKeyValueStore {
    override fun current(): Int? {
        val number = sharedPreferences.getString("Current", null)
        if (number == null) return null
        return number.toInt()
    }

    override fun current(number: Int) {
        val editor = sharedPreferences.edit()
        editor.putString("Current", number.toString())
        editor.apply() // commit?
    }

    override fun latest(): Int? {
        val number = sharedPreferences.getString("Latest", null)
        if (number == null) return null
        return number.toInt()
    }

    override fun latest(number: Int) {
        val editor = sharedPreferences.edit()
        editor.putString("Latest", number.toString())
        editor.apply() // commit?
    }
}