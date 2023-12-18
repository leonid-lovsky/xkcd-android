package com.example.xkcd_android

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class ComicApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(ThreadPolicy.Builder()
                .detectAll().penaltyLog().build()
            )
            StrictMode.setVmPolicy(VmPolicy.Builder()
                .detectAll().penaltyLog().build()
            )
            Timber.plant(DebugTree())
        }
    }
}
