package com.example.xkcd_android

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import java.util.concurrent.Executors

class ComicApplication : Application() {

    private lateinit var comicPresenter: ComicPresenter

    fun comicPresenter() = comicPresenter

    override fun onCreate() {
        super.onCreate()
        StrictMode.setThreadPolicy(ThreadPolicy.Builder().detectAll().penaltyLog().build())
        StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().penaltyLog().build())
        val availableProcessors = Runtime.getRuntime().availableProcessors()
        val backgroundExecutor = Executors.newFixedThreadPool(availableProcessors)
        val mainThreadExecutor = MainThreadExecutor()
        val roomDependency = RoomDependency(this)
        val localComicDataSource = roomDependency.dataSource()
        val retrofitDependency = RetrofitDependency()
        val remoteComicDataSource = retrofitDependency.dataSource()
        val androidDependency = AndroidDependency(this)
        val comicStateStore = androidDependency.stateStore()
        val comicInteractor: ComicInteractor
        val comicPresenter: ComicPresenter
    }
}