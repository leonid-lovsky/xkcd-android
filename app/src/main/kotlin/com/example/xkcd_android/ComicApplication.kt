package com.example.xkcd_android

import android.app.Application
import java.util.concurrent.Executors

class ComicApplication : Application(), ComicDependencies {
    private val comicDependenciesLocal = ComicDependenciesRoom(this)
    private val comicDependenciesRemote = ComicDependenciesRetrofit()
    private val comicStorageLocal = comicDependenciesLocal.comicStorageLocal()
    private val comicStorageRemote = comicDependenciesRemote.comicStorageRemote()
    private val executorServiceWorker = Executors.newFixedThreadPool(3)
    private val comicInteractor = ComicInteractor(comicStorageLocal, comicStorageRemote, executorServiceWorker)
    private val comicController = ComicController(comicInteractor)

    override fun comicController(): ComicController {
        return comicController
    }
}