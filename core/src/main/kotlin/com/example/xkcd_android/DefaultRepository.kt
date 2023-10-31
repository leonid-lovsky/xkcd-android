package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicLocalStorage
import com.example.xkcd_android.contract.ComicPreferences
import com.example.xkcd_android.contract.ComicRemoteStorage
import com.example.xkcd_android.contract.ComicRepository
import com.example.xkcd_android.data.Comic
import com.example.xkcd_android.function.Callback
import com.example.xkcd_android.function.Resource
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

class DefaultRepository(
    private val localStorage: ComicLocalStorage,
    private val remoteStorage: ComicRemoteStorage,
    private val preferences: ComicPreferences,
    private val backgroundExecutor: ExecutorService,
    private val mainThreadExecutor: Executor
) : ComicRepository {

    override fun comic(callback: Resource<Comic>) {
        TODO("Not yet implemented")
    }

    override fun comic(number: Int, callback: Resource<Comic>) {
        TODO("Not yet implemented")
    }

    override fun comic(comic: Comic) {
        TODO("Not yet implemented")
    }

    override fun current(callback: Callback<Int>) {
        TODO("Not yet implemented")
    }

    override fun current(number: Int) {
        TODO("Not yet implemented")
    }

    override fun latest(callback: Callback<Int>) {
        TODO("Not yet implemented")
    }

    override fun latest(number: Int) {
        TODO("Not yet implemented")
    }

    fun comic(callback: Callback<Resource<Comic>>) {
        backgroundExecutor.execute {
            mainThreadExecutor.execute {
                val resource = Resource(true, null, null)
                callback.invoke(resource)
            }
            val comicDataRemote = remoteStorage.comic()
            if (comicDataRemote != null) {
                localStorage.comic(comicDataRemote)
            }
            val comicLocal = localStorage.comic()
            mainThreadExecutor.execute {
                val resource = Resource(false, comicLocal, null)
                callback.invoke(resource)
            }
        }
    }

    fun comic(number: Int, callback: Callback<Resource<Comic>>) {
        backgroundExecutor.execute {
            mainThreadExecutor.execute {
                val resource = Resource(true, null, null)
                callback.invoke(resource)
            }
            val comicDataRemote = remoteStorage.comic()
            if (comicDataRemote != null) {
                localStorage.comic(comicDataRemote)
            }
            val comicLocal = localStorage.comic(number)
            mainThreadExecutor.execute {
                val resource = Resource(false, comicLocal, null)
                callback.invoke(resource)
            }
        }
    }
}