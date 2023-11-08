package com.example.xkcd_android

import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

class CoreRepository(
    private val localStorage: ComicStorage,
    private val remoteStorage: ComicRemoteStorage,
    private val keyValueStore: ComicKeyValueStore,
    private val backgroundExecutor: ExecutorService,
    private val mainThreadExecutor: Executor
) : ComicRepository {
    override fun loadLatestComic(callback: Callback<Resource<ComicData>>) {
        backgroundExecutor.execute {
            mainThreadExecutor.execute {
                val resource = Resource<ComicData>(false)
                callback.invoke(resource)
            }
            val remoteComic = remoteStorage.loadLatestComic()
            if (remoteComic != null) {
                localStorage.saveComic(remoteComic)
            }
            val localComic = localStorage.loadLatestComic()
            mainThreadExecutor.execute {
                val resource = Resource(data = localComic)
                callback.invoke(resource)
            }
        }
    }

    override fun loadComicByNumber(number: Int, callback: Callback<Resource<ComicData>>) {
        backgroundExecutor.execute {
            mainThreadExecutor.execute {
                val resource = Resource<ComicData>(false)
                callback.invoke(resource)
            }
            val remoteComic = remoteStorage.loadComicByNumber(number)
            if (remoteComic != null) {
                localStorage.saveComic(remoteComic)
            }
            val localComic = localStorage.loadComicByNumber(number)
            mainThreadExecutor.execute {
                val resource = Resource(data = localComic)
                callback.invoke(resource)
            }
        }
    }

    override fun loadCurrentNumber(callback: Callback<Int?>) {
        backgroundExecutor.execute {
            val current = keyValueStore.loadCurrentNumber()
            mainThreadExecutor.execute {
                callback.invoke(current)
            }
        }
    }

    override fun saveCurrentNumber(number: Int) {
        backgroundExecutor.execute {
            keyValueStore.saveCurrentNumber(number)
        }
    }

    override fun loadLatestNumber(callback: Callback<Int?>) {
        backgroundExecutor.execute {
            val current = keyValueStore.loadCurrentNumber()
            mainThreadExecutor.execute {
                callback.invoke(current)
            }
        }
    }

    override fun saveLatestNumber(number: Int) {
        backgroundExecutor.execute {
            keyValueStore.saveLatestNumber(number)
        }
    }
}