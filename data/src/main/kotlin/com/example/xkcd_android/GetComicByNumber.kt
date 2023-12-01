package com.example.xkcd_android

import java.util.concurrent.Executor

class GetComicByNumber(
    private val localComicStorage: LocalComicStorage,
    private val remoteComicStorage: RemoteComicStorage,
) {

    operator fun invoke(number: Int): Comic? {
        val remoteComic = remoteComicStorage.getComicByNumber(number)
        if (remoteComic != null) {
            localComicStorage.putComic(remoteComic)
            return remoteComic
        }
        val localComic = localComicStorage.getComicByNumber(number)
        if (localComic != null) {
            return localComic
        }
        return null
    }
}