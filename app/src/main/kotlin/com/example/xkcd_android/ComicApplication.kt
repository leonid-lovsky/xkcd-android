package com.example.xkcd_android

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ComicApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        StrictMode.setThreadPolicy(ThreadPolicy.Builder().detectAll().penaltyLog().penaltyDeath().build())
        StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().penaltyLog().penaltyDeath().build())
    }
}
