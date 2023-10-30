package com.example.xkcd_android

import com.example.xkcd_android.function.Callback
import com.example.xkcd_android.storage.LocalStorage
import com.example.xkcd_android.storage.RemoteStorage
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

class ComicRepositoryDefault(
    private val localStorage: LocalStorage,
    private val remoteStorage: RemoteStorage,
    private val executorServiceBackground: ExecutorService,
    private val executorMain: Executor
) {

    fun comic(callback: Callback<Resource<Comic>>) {
        executorServiceBackground.execute {
            executorMain.execute {
                val resource = Resource(true, null, null)
                callback.invoke(resource)
            }
            val comicDataRemote = remoteStorage.comic()
            if (comicDataRemote != null) {
                localStorage.comic(comicDataRemote)
            }
            val comicLocal = localStorage.comic()
            executorMain.execute {
                val resource = Resource(false, comicLocal, null)
                callback.invoke(resource)
            }
        }
    }

    fun comic(number: Int, callback: Callback<Resource<Comic>>) {
        executorServiceBackground.execute {
            executorMain.execute {
                val resource = Resource(true, null, null)
                callback.invoke(resource)
            }
            val comicDataRemote = remoteStorage.comic()
            if (comicDataRemote != null) {
                localStorage.comic(comicDataRemote)
            }
            val comicLocal = localStorage.comic(number)
            executorMain.execute {
                val resource = Resource(false, comicLocal, null)
                callback.invoke(resource)
            }
        }
    }
}