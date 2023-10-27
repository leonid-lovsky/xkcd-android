package com.example.xkcd_android

import android.app.Application
import java.util.concurrent.Executors

class ComicApplication : Application() {
    private val threadPoolSize = Runtime.getRuntime().availableProcessors()
    private val comicDependenciesLocal = ComicModuleRoom(this)
    private val comicDependenciesRemote = ComicModuleRetrofit()
    private val comicStorageLocal = comicDependenciesLocal.comicStorageLocal()
    private val comicStorageRemote = comicDependenciesRemote.comicStorageRemote()
    private val executorServiceBackground = Executors.newFixedThreadPool(threadPoolSize)
    private val comicRepository = ComicRepository(comicStorageLocal, comicStorageRemote, executorServiceBackground)
    private val comicPresenter = ComicPresenter(comicRepository)

    fun comicController(): ComicPresenter {
        return comicPresenter
    }
}