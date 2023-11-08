package com.example.xkcd_android

import android.app.Application
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ComicApplication : Application() {
    private val availableProcessors: Int = Runtime.getRuntime().availableProcessors()

    private lateinit var backgroundExecutor: ExecutorService
    private lateinit var mainThreadExecutor: Executor

    private lateinit var localStorage: ComicStorage

    private lateinit var remoteStorage: ComicRemoteStorage

    private lateinit var keyValueStore: ComicKeyValueStore

    private lateinit var repository: ComicRepository

    private lateinit var presenter: ComicPresenter

    fun presenter() = presenter // dependency injection

    override fun onCreate() {
        super.onCreate()
        backgroundExecutor = Executors.newFixedThreadPool(availableProcessors)
        mainThreadExecutor = MainThreadExecutor()
        localStorage = localStorageModule.localStorage()
        remoteStorage = remoteStorageModule.remoteStorage()
        keyValueStore = keyValueStoreModule.keyValueStore()
        repository = repositoryModule.repository()
        presenter = presenterModule.presenter()
    }
}