package com.example.xkcd_android

@Suppress("unused")
object Constants {
    private const val CURRENT_COMIC_LINK = "https://xkcd.com/info.0.json"
    private const val COMIC_LINK_BY_NUMBER = "https://xkcd.com/%d/info.0.json"

    private fun getComicLinkByNumber(number: Int): String {
        return String.format(COMIC_LINK_BY_NUMBER, number)
    }
}