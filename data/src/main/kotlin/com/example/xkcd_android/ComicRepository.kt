package com.example.xkcd_android

class ComicRepository(
    private val localComicStorage: LocalComicStorage,
    private val remoteComicStorage: RemoteComicStorage,
) {

    fun getLatestComic(): Comic? {
        val remoteComic = remoteComicStorage.getLatestComic()
        if (remoteComic != null) {
            localComicStorage.putComic(remoteComic)
            return remoteComic
        }
        return null
    }

    fun getComicByNumber(number: Int): Comic? {
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