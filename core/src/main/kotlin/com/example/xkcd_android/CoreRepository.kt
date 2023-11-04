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
    override fun loadLatestComic(callback: Resource<Comic?>) {
        backgroundExecutor.execute {
            mainThreadExecutor.execute {
                callback.loading()
            }
            val remoteComic = remoteStorage.loadLatestComic()
            if (remoteComic != null) {
                localStorage.saveComic(remoteComic)
            }
            val localComic = localStorage.loadLatestComic()
            mainThreadExecutor.execute {
                callback.success(localComic)
            }
        }
    }

    override fun loadComicByNumber(number: Int, callback: Resource<Comic?>) {
        backgroundExecutor.execute {
            mainThreadExecutor.execute {
                callback.loading()
            }
            val remoteComic = remoteStorage.loadComicByNumber(number)
            if (remoteComic != null) {
                localStorage.saveComic(remoteComic)
            }
            val localComic = localStorage.loadComicByNumber(number)
            mainThreadExecutor.execute {
                callback.success(localComic)
            }
        }
    }

    override fun loadCurrentComicNumber(callback: Callback<Int?>) {
        backgroundExecutor.execute {
            val current = keyValueStore.loadCurrentNumber()
            mainThreadExecutor.execute {
                callback.callback(current)
            }
        }
    }

    override fun saveCurrentComicNumber(number: Int) {
        backgroundExecutor.execute {
            keyValueStore.saveCurrentNumber(number)
        }
    }

    override fun loadLatestComicNumber(callback: Callback<Int?>) {
        backgroundExecutor.execute {
            val current = keyValueStore.loadCurrentNumber()
            mainThreadExecutor.execute {
                callback.callback(current)
            }
        }
    }

    override fun saveLatestComicNumber(number: Int) {
        backgroundExecutor.execute {
            keyValueStore.saveLatestNumber(number)
        }
    }
}