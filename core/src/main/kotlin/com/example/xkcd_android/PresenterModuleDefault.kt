package com.example.xkcd_android

import com.example.xkcd_android.module.PresenterModule
import com.example.xkcd_android.module.RepositoryModule

class PresenterModuleDefault(
    repositoryModule: RepositoryModule
) : PresenterModule {
    private val repository = repositoryModule.repository()
    private val presenter = ComicPresenterDefault(repository)

    override fun presenter() = presenter
}