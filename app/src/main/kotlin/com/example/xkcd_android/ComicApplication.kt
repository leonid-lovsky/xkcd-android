package com.example.xkcd_android

import android.app.Application
import java.util.concurrent.Executors

class ComicApplication : Application() {
    private val threadPoolSize = Runtime.getRuntime().availableProcessors()
    private val executorServiceBackground = Executors.newFixedThreadPool(threadPoolSize)
    private val mainThreadExecutor = MainThreadExecutor()

    private val comicDependenciesLocal = RoomModule(this)
    private val comicDependenciesRemote = RetrofitModule()

    private val comicStorageLocal = comicDependenciesLocal.storage()
    private val comicStorageRemote = comicDependenciesRemote.storage()

    private val comicRepository = ComicRepositoryDefault(
        comicStorageLocal, comicStorageRemote, executorServiceBackground, mainThreadExecutor
    )
    private val comicPresenter = ComicPresenterDefault(comicRepository)

    fun comicController(): ComicPresenterDefault {
        return comicPresenter
    }
}