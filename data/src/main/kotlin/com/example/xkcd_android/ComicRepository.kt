package com.example.xkcd_android

import java.util.concurrent.Executor

class ComicRepository(
    private val localComicStorage: LocalComicStorage,
    private val remoteComicStorage: RemoteComicStorage,
    private val mainThreadExecutor: Executor,
    private val backgroundExecutor: Executor,
) {

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