package com.example.xkcd_android

import android.app.Application

class ComicApplication : Application(), ComicDependencies {
    private val comicDependenciesLocal = ComicDependenciesRoom(this)
    private val comicDependenciesRemote = ComicDependenciesRetrofit()
    private val comicStorageLocal = comicDependenciesLocal.comicStorageLocal()
    private val comicStorageRemote = comicDependenciesRemote.comicStorageRemote()
    private val comicRepository = ComicRepository(comicStorageLocal, comicStorageRemote)
    private val comicController = ComicController(comicRepository)

    override fun comicController(): ComicController {
        return comicController
    }
}