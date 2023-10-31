package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicLocalStorage
import com.example.xkcd_android.contract.ComicKeyValueStore
import com.example.xkcd_android.contract.ComicRemoteStorage
import com.example.xkcd_android.module.RepositoryModule
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

class CoreRepositoryModule(
    localStorage: ComicLocalStorage,
    remoteStorage: ComicRemoteStorage,
    preferences: ComicKeyValueStore,
    backgroundExecutor: ExecutorService,
    mainThreadExecutor: Executor
) : RepositoryModule {
    private val repository = CoreRepository(
        localStorage, remoteStorage, preferences, backgroundExecutor, mainThreadExecutor
    )

    override fun repository() = repository
}