package com.example.xkcd_android

import android.app.Application
import com.example.xkcd_android.module.PresenterModule
import java.util.concurrent.Executors

class MyApplication : Application(), PresenterModule {
    private val availableProcessors = Runtime.getRuntime().availableProcessors()
    private val backgroundExecutor = Executors.newFixedThreadPool(availableProcessors)
    private val mainThreadExecutor = MainThreadExecutor()

    private val localStorageModule = RoomModule(this)
    private val remoteStorageModule = RetrofitModule()

    private val preferencesModule = DefaultPreferencesModule()

    private val repositoryModule = DefaultRepositoryModule(
        localStorageModule, remoteStorageModule, preferencesModule,
        backgroundExecutor, mainThreadExecutor
    )

    private val presenterModule = DefaultPresenterModule(repositoryModule)

    private val presenter = presenterModule.presenter()

    override fun presenter() = presenter
}