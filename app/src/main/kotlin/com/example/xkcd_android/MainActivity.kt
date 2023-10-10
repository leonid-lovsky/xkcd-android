package com.example.xkcd_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val CURRENT_COMIC_LINK = "https://xkcd.com/info.0.json"
    private val COMIC_LINK_BY_NUMBER = "https://xkcd.com/%d/info.0.json"

    private fun get_comic_link_by_number(number: Int): String {
        return String.format(COMIC_LINK_BY_NUMBER, number)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}