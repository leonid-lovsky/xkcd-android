package com.example.xkcd_android

import android.content.Context
import androidx.room.Room
import com.example.xkcd_android.module.StorageModule

class RoomModule(applicationContext: Context) : StorageModule {
    private val database = Room.databaseBuilder(
        applicationContext, MainDatabase::class.java, "database"
    ).build()

    private val service = database.service()
    private val converter = RoomConverter()
    private val storage = RoomStorage(service, converter)

    override fun storage() = storage
}