package com.example.xkcd_android

import com.example.xkcd_android.module.LocalStorageModule
import com.example.xkcd_android.module.PreferencesModule
import com.example.xkcd_android.module.RemoteStorageModule
import com.example.xkcd_android.module.RepositoryModule
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

class DefaultRepositoryModule(
    localStorageModule: LocalStorageModule,
    remoteModule: RemoteStorageModule,
    preferencesModule: PreferencesModule,
    backgroundExecutor: ExecutorService,
    mainThreadExecutor: Executor
) : RepositoryModule {
    private val localStorage = localStorageModule.localStorage()
    private val remoteStorage = remoteModule.localStorage()
    private val preferences = preferencesModule.preferences()
    // private val backgroundExecutor = backgroundExecutor
    // private val mainThreadExecutor = mainThreadExecutor

    private val repository = DefaultComicRepository(
        localStorage, remoteStorage, preferences, backgroundExecutor, mainThreadExecutor
    )

    override fun repository() = repository
}