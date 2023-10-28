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
                val resource = Resource(Status.LOADING, null)
                callback.onChanged(resource)
            }
            val comicRemote = comicStorageRemote.comic()
            comicStorageLocal.comic(comicRemote)
            val comicLocal = comicStorageLocal.comic()
            executorMain.execute {
                val resource = Resource(Status.SUCCESS, comicLocal)
                callback.onChanged(resource)
            }
        }
    }

    fun comic(number: Int, callback: Callback<Resource<Comic>>) {
        executorServiceBackground.execute {
            executorMain.execute {
                val resource = Resource(Status.LOADING, null)
                callback.onChanged(resource)
            }
            val comicRemote = comicStorageRemote.comic(number)
            comicStorageLocal.comic(comicRemote)
            val comicLocal = comicStorageLocal.comic(number)
            executorMain.execute {
                val resource = Resource(Status.SUCCESS, comicLocal)
                callback.onChanged(resource)
            }
        }
    }
}