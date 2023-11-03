package com.example.xkcd_android

import android.content.Context
import androidx.room.Room
import com.example.xkcd_android.module.LocalStorageModule

class RoomModule(applicationContext: Context) : LocalStorageModule {
    private val database = Room.databaseBuilder(
        applicationContext, MainDatabase::class.java, "database"
    ).build()

    private val service = database.service()
    private val converter = RoomConverter()

    private val localStorage = RoomStorage(service, converter)

    override fun localStorage() = localStorage
}