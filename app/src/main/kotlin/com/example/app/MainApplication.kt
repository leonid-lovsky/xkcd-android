package com.example.app

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import com.example.retrofit.RetrofitModule
import com.example.room.RoomModule
import java.util.concurrent.Executors

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        StrictMode.setThreadPolicy(ThreadPolicy.Builder().detectAll().penaltyLog().build())
        StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().penaltyLog().build())
        val appModule = AppModule(applicationContext = applicationContext)
        val roomModule = RoomModule(applicationContext = applicationContext)
        val retrofitModule = RetrofitModule()
        val availableProcessors = Runtime.getRuntime().availableProcessors()
        val backgroundExecutor = Executors.newFixedThreadPool(availableProcessors)
        val mainThreadExecutor = MainThreadExecutor()
    }
}