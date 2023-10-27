package com.example.xkcd_android

import java.util.concurrent.ExecutorService

class ComicInteractor(
    private val comicStorageLocal: ComicStorageLocal,
    private val comicStorageRemote: ComicStorageRemote,
    private val executorService: ExecutorService
) {

    fun comic(comicCallback: ComicCallback) {
        executorService.execute {
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
        executorService.execute {
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