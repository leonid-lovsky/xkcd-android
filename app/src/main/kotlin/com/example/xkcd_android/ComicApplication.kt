package com.example.xkcd_android

import android.app.Application
import android.content.SharedPreferences
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

@HiltAndroidApp
class ComicApplication : Application() {

    @Inject lateinit var preferences: SharedPreferences

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
