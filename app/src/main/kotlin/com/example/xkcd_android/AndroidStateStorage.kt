package com.example.xkcd_android

import android.annotation.SuppressLint
import android.content.SharedPreferences

class AndroidStateStorage(private val sharedPreferences: SharedPreferences) : ComicStateStore {

    private val currentComicNumberKey = "Current comic number"
    private val latestComicNumberKey = "Latest comic number"

    override fun loadCurrentNumber(): Int? {
        val number = sharedPreferences.getString(currentComicNumberKey, null)
        if (number == null) return null
        return number.toInt()
    }

    @SuppressLint("ApplySharedPref")
    override fun saveCurrentNumber(number: Int) {
        val editor = sharedPreferences.edit()
        editor.putString(currentComicNumberKey, number.toString())
        editor.commit()
    }

    override fun loadLatestNumber(): Int? {
        val number = sharedPreferences.getString(latestComicNumberKey, null)
        if (number == null) return null
        return number.toInt()
    }

    @SuppressLint("ApplySharedPref")
    override fun saveLatestNumber(number: Int) {
        val editor = sharedPreferences.edit()
        editor.putString(latestComicNumberKey, number.toString())
        editor.commit()
    }
}