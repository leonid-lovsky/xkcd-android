package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicRepository
import com.example.xkcd_android.module.PresenterModule

class CorePresenterModule(repository: ComicRepository) : PresenterModule {
    private val presenter = CorePresenter(repository)

    override fun presenter() = presenter
}