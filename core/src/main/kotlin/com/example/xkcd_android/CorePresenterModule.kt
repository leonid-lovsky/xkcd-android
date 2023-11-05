package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicRepository
import com.example.xkcd_android.module.ComicPresenterModule

class CorePresenterModule(repository: ComicRepository) : ComicPresenterModule {
    private val presenter = CorePresenter(repository)

    override fun presenter() = presenter
}