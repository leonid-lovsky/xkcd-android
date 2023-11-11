package com.example.xkcd_android

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import java.util.concurrent.Executors

class MainApplication : Application() {

    private lateinit var comicViewController: ComicViewController

    fun comicPresenter() = comicViewController

    override fun onCreate() {
        super.onCreate()
        StrictMode.setThreadPolicy(ThreadPolicy.Builder().detectAll().penaltyLog().build())
        StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().penaltyLog().build())
        val availableProcessors = Runtime.getRuntime().availableProcessors()
        val backgroundExecutor = Executors.newFixedThreadPool(availableProcessors)
        val mainThreadExecutor = MainThreadExecutor()
        val roomModule = RoomModule(this)
        val roomStorage = roomModule.roomStorage()
        val retrofitModule = RetrofitModule()
        val retrofitStorage = retrofitModule.retrofitStorage()
        val appModule = AppModule(this)
        val appPreferences = appModule.appPreferences()
    }
}