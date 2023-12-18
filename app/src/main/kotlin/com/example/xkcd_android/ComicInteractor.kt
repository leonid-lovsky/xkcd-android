package com.example.xkcd_android

import javax.inject.Inject

class ComicInteractor @Inject constructor(
    private val comicService: ComicService,
    private val comicDao: ComicDao,
) {

    suspend fun getLatestComic(): Comic {
        return try {
            val comic = comicService.getLatestComic()
            val ids = comicDao.putComic(comic)
            comicDao.getLatestComic()
        } catch (e: Throwable) {
            comicDao.getLatestComic()
        }
    }

    suspend fun getComic(number: Int): Comic {
        return try {
            comicDao.getComic(number)
        } catch (e: Throwable) {
            val comic = comicService.getComic(number)
            val ids = comicDao.putComic(comic)
            comicDao.getComic(comic.num)
        }
    }
}
