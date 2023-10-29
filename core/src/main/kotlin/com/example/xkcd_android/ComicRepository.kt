package com.example.xkcd_android

import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

class ComicRepository(
    private val comicStorageLocal: ComicStorageLocal,
    private val comicStorageRemote: ComicStorageRemote,
    private val executorServiceBackground: ExecutorService,
    private val executorMain: Executor
) {

    fun comic(callback: Callback<Resource<Comic>>) {
        executorServiceBackground.execute {
            executorMain.execute {
                val resource = Resource(true, null, null)
                callback.onChanged(resource)
            }
            val comicDataRemote = comicStorageRemote.comic()
            if (comicDataRemote != null) {
                comicStorageLocal.comic(comicDataRemote)
            }
            val comicLocal = comicStorageLocal.comic()
            executorMain.execute {
                val resource = Resource(false, comicLocal, null)
                callback.onChanged(resource)
            }
        }
    }

    fun comic(number: Int, callback: Callback<Resource<Comic>>) {
        executorServiceBackground.execute {
            executorMain.execute {
                val resource = Resource(true, null, null)
                callback.onChanged(resource)
            }
            val comicDataRemote = comicStorageRemote.comic()
            if (comicDataRemote != null) {
                comicStorageLocal.comic(comicDataRemote)
            }
            val comicLocal = comicStorageLocal.comic(number)
            executorMain.execute {
                val resource = Resource(false, comicLocal, null)
                callback.onChanged(resource)
            }
        }
    }
}