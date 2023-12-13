package com.example.xkcd_android

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy

class ComicApplication : Application() {

    val comicRepository: ComicRepository get() = ComicRepository()

    override fun onCreate() {
        super.onCreate()
        StrictMode.setThreadPolicy(ThreadPolicy.Builder().detectAll().penaltyLog().penaltyDeath().build())
        StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().penaltyLog().penaltyDeath().build())
    }
}
