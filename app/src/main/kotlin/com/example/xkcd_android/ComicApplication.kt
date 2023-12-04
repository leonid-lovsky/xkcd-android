package com.example.xkcd_android

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import java.util.concurrent.Executors

class ComicApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        StrictMode.setThreadPolicy(ThreadPolicy.Builder().detectAll().penaltyLog().build())
        StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().penaltyLog().build())
        val availableProcessors = Runtime.getRuntime().availableProcessors()
        val backgroundExecutor = Executors.newFixedThreadPool(availableProcessors)
        val mainThreadExecutor = MainThreadExecutor()
    }
}