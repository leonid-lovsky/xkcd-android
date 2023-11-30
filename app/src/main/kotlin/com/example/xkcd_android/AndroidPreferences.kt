package com.example.xkcd_android

import android.content.SharedPreferences

class AndroidPreferences(private val sharedPreferences: SharedPreferences) {

//    override fun loadCurrentNumber(): Int? {
    //        val number = sharedPreferences.getString(CURRENT_COMIC_NUMBER, null)
    //        if (number == null) return null
    //        return number.toInt()
    //    }
    //
    //    @SuppressLint("ApplySharedPref")
    //    override fun saveCurrentNumber(number: Int) {
    //        val editor = sharedPreferences.edit()
    //        editor.putString(CURRENT_COMIC_NUMBER, number.toString())
    //        editor.commit()
    //    }
    //
    //    override fun loadLatestNumber(): Int? {
    //        val number = sharedPreferences.getString(LATEST_COMIC_NUMBER, null)
    //        if (number == null) return null
    //        return number.toInt()
    //    }
    //
    //    @SuppressLint("ApplySharedPref")
    //    override fun saveLatestNumber(number: Int) {
    //        val editor = sharedPreferences.edit()
    //        editor.putString(LATEST_COMIC_NUMBER, number.toString())
    //        editor.commit()
    //    }

    companion object {

        const val CURRENT_COMIC_NUMBER = "CURRENT_COMIC_NUMBER"
        const val LATEST_COMIC_NUMBER = "LATEST_COMIC_NUMBER"
    }
}