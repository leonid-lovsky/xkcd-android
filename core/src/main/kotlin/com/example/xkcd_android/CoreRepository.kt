package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicKeyValueStore
import com.example.xkcd_android.contract.ComicLocalStorage
import com.example.xkcd_android.contract.ComicRemoteStorage
import com.example.xkcd_android.contract.ComicRepository
import com.example.xkcd_android.data.Callback
import com.example.xkcd_android.data.Comic
import com.example.xkcd_android.data.Resource
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

class CoreRepository(
    private val localStorage: ComicLocalStorage,
    private val remoteStorage: ComicRemoteStorage,
    private val keyValueStore: ComicKeyValueStore,
    private val backgroundExecutor: ExecutorService,
    private val mainThreadExecutor: Executor
) : ComicRepository {
    override fun loadLatestComic(callback: Callback<Resource<Comic>>) {
        backgroundExecutor.execute {
            mainThreadExecutor.execute {
                val resource = Resource<Comic>(false)
                callback.callback(resource)
            }
            val remoteComic = remoteStorage.loadLatestComic()
            if (remoteComic != null) {
                localStorage.saveComic(remoteComic)
            }
            val localComic = localStorage.loadLatestComic()
            mainThreadExecutor.execute {
                val resource = Resource(data = localComic)
                callback.callback(resource)
            }
        }
    }

    override fun loadComicByNumber(number: Int, callback: Callback<Resource<Comic>>) {
        backgroundExecutor.execute {
            mainThreadExecutor.execute {
                val resource = Resource<Comic>(false)
                callback.callback(resource)
            }
            val remoteComic = remoteStorage.loadComicByNumber(number)
            if (remoteComic != null) {
                localStorage.saveComic(remoteComic)
            }
            val localComic = localStorage.loadComicByNumber(number)
            mainThreadExecutor.execute {
                val resource = Resource(data = localComic)
                callback.callback(resource)
            }
        }
    }

    override fun loadCurrentNumber(callback: Callback<Int?>) {
        backgroundExecutor.execute {
            val current = keyValueStore.loadCurrentNumber()
            mainThreadExecutor.execute {
                callback.callback(current)
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
                callback.callback(current)
            }
        }
    }

    override fun saveLatestNumber(number: Int) {
        backgroundExecutor.execute {
            keyValueStore.saveLatestNumber(number)
        }
    }
}