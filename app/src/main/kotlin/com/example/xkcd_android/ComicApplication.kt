package com.example.xkcd_android

import android.app.Application
import java.util.concurrent.Executors

class ComicApplication : Application() {
    private val availableProcessors = Runtime.getRuntime().availableProcessors()
    private val backgroundExecutor = Executors.newFixedThreadPool(availableProcessors)
    private val mainThreadExecutor = MainThreadExecutor()

    private val localStorageModule = RoomModule(this)
    private val localStorage = localStorageModule.localStorage()

    private val remoteStorageModule = RetrofitModule()
    private val remoteStorage = remoteStorageModule.remoteStorage()

    private val preferencesModule = AppPreferencesModule()
    private val preferences = preferencesModule.preferences()

    private val repositoryModule = CoreRepositoryModule(
        localStorage, remoteStorage, preferences,
        backgroundExecutor, mainThreadExecutor
    )
    private val repository = repositoryModule.repository()

    private val presenterModule = CorePresenterModule(repository)
    private val presenter = presenterModule.presenter()

    fun presenter() = presenter // interface
}