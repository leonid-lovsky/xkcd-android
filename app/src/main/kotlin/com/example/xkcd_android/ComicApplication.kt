package com.example.xkcd_android

import android.app.Application
import com.example.xkcd_android.contract.ComicKeyValueStore
import com.example.xkcd_android.contract.ComicLocalStorage
import com.example.xkcd_android.contract.ComicPresenter
import com.example.xkcd_android.contract.ComicRemoteStorage
import com.example.xkcd_android.contract.ComicRepository
import com.example.xkcd_android.module.ComicKeyValueStoreModule
import com.example.xkcd_android.module.ComicLocalStorageModule
import com.example.xkcd_android.module.ComicPresenterModule
import com.example.xkcd_android.module.ComicRemoteStorageModule
import com.example.xkcd_android.module.ComicRepositoryModule
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ComicApplication : Application() {
    private val availableProcessors: Int = Runtime.getRuntime().availableProcessors()

    private lateinit var backgroundExecutor: ExecutorService
    private lateinit var mainThreadExecutor: Executor

    private lateinit var localStorageModule: ComicLocalStorageModule
    private lateinit var localStorage: ComicLocalStorage

    private lateinit var remoteStorageModule: ComicRemoteStorageModule
    private lateinit var remoteStorage: ComicRemoteStorage

    private lateinit var keyValueStoreModule: ComicKeyValueStoreModule
    private lateinit var keyValueStore: ComicKeyValueStore

    private lateinit var repositoryModule: ComicRepositoryModule
    private lateinit var repository: ComicRepository

    private lateinit var presenterModule: ComicPresenterModule
    private lateinit var presenter: ComicPresenter

    fun presenter() = presenter // module interface?

    override fun onCreate() {
        super.onCreate()
        backgroundExecutor = Executors.newFixedThreadPool(availableProcessors)
        mainThreadExecutor = MainThreadExecutor()
        localStorageModule = RoomModule(this)
        localStorage = localStorageModule.localStorage()
        remoteStorageModule = RetrofitModule()
        remoteStorage = remoteStorageModule.remoteStorage()
        keyValueStoreModule = AppKeyValueStoreModule(this)
        keyValueStore = keyValueStoreModule.keyValueStore()
        repositoryModule = CoreRepositoryModule(localStorage, remoteStorage, keyValueStore, backgroundExecutor, mainThreadExecutor)
        repository = repositoryModule.repository()
        presenterModule = CorePresenterModule(repository)
        presenter = presenterModule.presenter()
    }
}