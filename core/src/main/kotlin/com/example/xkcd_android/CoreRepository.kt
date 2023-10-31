package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicLocalStorage
import com.example.xkcd_android.contract.ComicKeyValueStore
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
    private val preferences: ComicKeyValueStore,
    private val backgroundExecutor: ExecutorService,
    private val mainThreadExecutor: Executor
) : ComicRepository {

    override fun comic(callback: Resource<Comic>) {
        backgroundExecutor.execute {
            mainThreadExecutor.execute {
                callback.loading()
            }
            val remoteComic = remoteStorage.comic()
            if (remoteComic != null) {
                localStorage.comic(remoteComic)
            }
            val localComic = localStorage.comic()
            mainThreadExecutor.execute {
                if (localComic != null) {
                    callback.success(localComic)
                }
            }
        }
    }

    override fun comic(number: Int, callback: Resource<Comic>) {
        backgroundExecutor.execute {
            mainThreadExecutor.execute {
                callback.loading()
            }
            val remoteComic = remoteStorage.comic(number)
            if (remoteComic != null) {
                localStorage.comic(remoteComic)
            }
            val localComic = localStorage.comic(number)
            mainThreadExecutor.execute {
                if (localComic != null) {
                    callback.success(localComic)
                }
            }
        }
    }

    override fun current(callback: Callback<Int>) {
        backgroundExecutor.execute {
            val current = preferences.current()
            mainThreadExecutor.execute {
                if (current != null) {
                    callback.invoke(current)
                }
            }
        }
    }

    override fun current(number: Int) {
        backgroundExecutor.execute {
            preferences.current(number)
        }
    }

    override fun latest(callback: Callback<Int>) {
        backgroundExecutor.execute {
            val current = preferences.current()
            mainThreadExecutor.execute {
                if (current != null) {
                    callback.invoke(current)
                }
            }
        }
    }

    override fun latest(number: Int) {
        backgroundExecutor.execute {
            preferences.latest(number)
        }
    }
}