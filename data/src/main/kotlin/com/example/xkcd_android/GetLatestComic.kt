package com.example.xkcd_android

import java.util.concurrent.Executor

class GetLatestComic(
    private val localComicStorage: LocalComicStorage,
    private val remoteComicStorage: RemoteComicStorage,
) {

    operator fun invoke(): Comic? {
        val remoteComic = remoteComicStorage.getLatestComic()
        if (remoteComic != null) {
            localComicStorage.putComic(remoteComic)
            return remoteComic
        }
        return null
    }
}