package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicPresenter
import com.example.xkcd_android.contract.ComicRepository
import com.example.xkcd_android.module.PresenterModule
import com.example.xkcd_android.module.RepositoryModule

class RepositoryModuleDefault(
) : RepositoryModule {
    private val repository = ComicRepositoryDefault()

    override fun repository() = repository
}