package com.example.xkcd_android

import java.util.concurrent.ExecutorService

class ComicInteractor(
    private val comicStorageLocal: ComicStorageLocal,
    private val comicStorageRemote: ComicStorageRemote,
    private val executorServiceWorker: ExecutorService
) {

    fun comic() {
        executorServiceWorker.execute {
            val comicRemote = comicStorageRemote.comic()
            if (comicRemote != null) {
                comicStorageLocal.comic(comicRemote)
            }
            val comicLocal = comicStorageLocal.comic()
        }
    }

    fun comic(number: Int) {
        executorServiceWorker.execute {
            val comicRemote = comicStorageRemote.comic(number)
            if (comicRemote != null) {
                comicStorageLocal.comic(comicRemote)
            }
            val comicLocal = comicStorageLocal.comic(number)
        }
    }
}