package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicPresenter
import com.example.xkcd_android.module.AppModule
import com.example.xkcd_android.module.CoreModule
import com.example.xkcd_android.module.LocalModule
import com.example.xkcd_android.module.RemoteModule

class CoreModuleDefault(
    appModule: AppModule,
    localModule: LocalModule,
    remoteModule: RemoteModule
): CoreModule {
    private val localStorage = localModule.storage()
    private val remoteStorage = remoteModule.storage()

    private val repository = ComicRepositoryDefault(localStorage, remoteStorage)
    private val presenter = ComicPresenterDefault(repository)

    override fun presenter(): ComicPresenter {
        return presenter
    }
}