package com.example.xkcd_android

import java.util.concurrent.ExecutorService

class ComicRepository(
    private val comicStorageLocal: ComicStorageLocal,
    private val comicStorageRemote: ComicStorageRemote,
    private val executorServiceBackground: ExecutorService
) {

    fun comic(comicCallback: ComicCallback) {
        executorServiceBackground.execute {
            val comicRemote = comicStorageRemote.comic()
            if (comicRemote != null) {
                comicStorageLocal.comic(comicRemote)
            }
            val comicLocal = comicStorageLocal.comic()
            if (comicLocal != null) {
                comicCallback.onChanged(comicLocal)
            }
        }
    }

    fun comic(number: Int, comicCallback: ComicCallback) {
        executorServiceBackground.execute {
            val comicRemote = comicStorageRemote.comic(number)
            if (comicRemote != null) {
                comicStorageLocal.comic(comicRemote)
            }
            val comicLocal = comicStorageLocal.comic(number)
            if (comicLocal != null) {
                comicCallback.onChanged(comicLocal)
            }
        }
    }
}