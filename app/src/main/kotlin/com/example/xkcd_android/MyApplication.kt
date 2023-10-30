package com.example.xkcd_android

import android.app.Application
import java.util.concurrent.Executors

class MyApplication : Application() {
    private val availableProcessors = Runtime.getRuntime().availableProcessors()
    private val backgroundExecutor = Executors.newFixedThreadPool(availableProcessors)
    private val mainThreadExecutor = MainThreadExecutor()

    private val roomModule = RoomModule(this)
    private val retrofitModule = RetrofitModule()

    private val roomStorage = roomModule.storage()
    private val retrofitStorage = retrofitModule.storage()

    private val comicRepository = ComicRepositoryDefault(
        roomStorage, retrofitStorage, backgroundExecutor, mainThreadExecutor
    )
    private val comicPresenter = ComicPresenterDefault(comicRepository)

    fun presenter() = comicPresenter
}