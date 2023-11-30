package com.example.xkcd_android

import java.util.concurrent.Executor

class ComicRepository(
    private val localComicStorage: LocalComicStorage,
    private val remoteComicStorage: RemoteComicStorage,
    private val comicPreferences: ComicPreferences,
    private val mainThreadExecutor: Executor,
    private val backgroundExecutor: Executor,
) {

    fun getLatestComic(): Comic? {
        val remoteComic = remoteComicStorage.getLatestComic()
        if (remoteComic != null) {
            localComicStorage.putComic(remoteComic)
            return remoteComic
        }
        val latestComicNumber = comicPreferences.getLatestComicNumber()
        if (latestComicNumber != null) {
            localComicStorage.getComicByNumber(latestComicNumber)
        }
        return null
    }

    fun getComicByNumber(number: Int): Comic? {
        val localComic = localComicStorage.getComicByNumber(number)
        if (localComic != null) {
            return localComic
        }
        val remoteComic = remoteComicStorage.getComicByNumber(number)
        if (remoteComic != null) {
            localComicStorage.putComic(remoteComic)
            return remoteComic
        }
        return null
    }
}