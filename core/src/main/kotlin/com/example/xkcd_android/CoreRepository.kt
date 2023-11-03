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

    override fun loadLatestComic(callback: Resource<Comic>) {
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
                if (localComic != null) {
                    callback.success(localComic)
                }
            }
        }
    }

    override fun loadComicByNumber(number: Int, callback: Resource<Comic>) {
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
                if (localComic != null) {
                    callback.success(localComic)
                }
            }
        }
    }

    override fun loadCurrentNumber(callback: Callback<Int>) {
        backgroundExecutor.execute {
            val current = keyValueStore.loadCurrentNumber()
            mainThreadExecutor.execute {
                if (current != null) {
                    callback.callback(current)
                }
            }
        }
    }

    override fun saveCurrentNumber(number: Int) {
        backgroundExecutor.execute {
            keyValueStore.saveCurrentNumber(number)
        }
    }

    override fun loadLatestNumber(callback: Callback<Int>) {
        backgroundExecutor.execute {
            val current = keyValueStore.loadCurrentNumber()
            mainThreadExecutor.execute {
                if (current != null) {
                    callback.callback(current)
                }
            }
        }
    }

    override fun saveLatestNumber(number: Int) {
        backgroundExecutor.execute {
            keyValueStore.saveLatestNumber(number)
        }
    }

    override fun loadCurrentComic(callback: Callback<Comic>) {
        TODO("Not yet implemented")
    }

    override fun saveCurrentComic(comic: Comic) {
        TODO("Not yet implemented")
    }
}