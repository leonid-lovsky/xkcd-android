package com.example.xkcd_android

import timber.log.Timber
import javax.inject.Inject

class ComicInteractor @Inject constructor(
    private val comicService: ComicService,
    private val comicDao: ComicDao,
) {

    suspend fun getLatestComic(forceRefresh: Boolean = true): Comic? {
        Timber.i("forceRefresh: $forceRefresh")
        if (!forceRefresh) {
            val cache = comicDao.getLatestComic()
            Timber.i("cache: $cache")
            if (cache != null) {
                return cache
            }
        }
        val response = comicService.getLatestComic()
        Timber.i("response: $response")
        val body = response.body()
        Timber.i("body: $body")
        if (body != null) {
            val ids = comicDao.putComic(body)
            Timber.i("ids: $ids")
            return body
        }
        val result = comicDao.getLatestComic()
        Timber.i("result: $result")
        return result
    }

    suspend fun getComic(number: Int, forceRefresh: Boolean = true): Comic? {
        Timber.i("number: $number, forceRefresh: $forceRefresh")
        if (!forceRefresh) {
            val cache = comicDao.getComic(number)
            Timber.i("cache: $cache")
            if (cache != null) {
                return cache
            }
        }
        val response = comicService.getComic(number)
        Timber.i("response: $response")
        val body = response.body()
        Timber.i("body: $body")
        if (body != null) {
            val ids = comicDao.putComic(body)
            Timber.i("ids: $ids")
            return body
        }
        val result = comicDao.getComic(number)
        Timber.i("result: $result")
        return result
    }
}
