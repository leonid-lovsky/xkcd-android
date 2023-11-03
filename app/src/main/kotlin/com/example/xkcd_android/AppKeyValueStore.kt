package com.example.xkcd_android

import android.content.SharedPreferences
import com.example.xkcd_android.contract.ComicKeyValueStore
import com.example.xkcd_android.data.Comic

class AppKeyValueStore(private val sharedPreferences: SharedPreferences) : ComicKeyValueStore {
    override fun loadCurrentNumber(): Int? {
        val number = sharedPreferences.getString("Current number", null)
        if (number == null) return null
        return number.toInt()
    }

    override fun saveCurrentNumber(number: Int) {
        val editor = sharedPreferences.edit()
        editor.putString("Current number", number.toString())
        editor.apply() // or commit?
    }

    override fun loadLatestNumber(): Int? {
        val number = sharedPreferences.getString("Latest number", null)
        if (number == null) return null
        return number.toInt()
    }

    override fun saveLatestNumber(number: Int) {
        val editor = sharedPreferences.edit()
        editor.putString("Latest number", number.toString())
        editor.apply() // or commit?
    }

    override fun loadCurrentComic(): Comic? {
        val comic = sharedPreferences.get("Current comic", null)
        if (number == null) return null
        return number.toInt()
    }

    override fun saveCurrentComic(comic: Comic) {
        TODO("Not yet implemented")
    }

    override fun loadLatestComic(): Comic? {
        TODO("Not yet implemented")
    }

    override fun saveLatestComic(comic: Comic) {
        TODO("Not yet implemented")
    }
}