package com.example.xkcd_android

import com.example.xkcd_android.module.PresenterModule
import com.example.xkcd_android.module.RepositoryModule

class DefaultPresenterModule(
    repositoryModule: RepositoryModule
) : PresenterModule {
    private val repository = repositoryModule.repository()

    private val presenter = DefaultComicPresenter(repository)

    override fun presenter() = presenter
}