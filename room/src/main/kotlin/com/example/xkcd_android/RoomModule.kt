package com.example.xkcd_android

import android.content.Context
import androidx.room.Room
import com.example.xkcd_android.storage.LocalStorage
import com.example.xkcd_android.module.LocalModule

class RoomModule(applicationContext: Context) : LocalModule {
    private val database = Room.databaseBuilder(
        applicationContext, MainDatabase::class.java, "database"
    ).build()

    private val service = database.service()
    private val converter = RoomConverter()
    private val storage = RoomStorage(service, converter)

    override fun storage(): LocalStorage {
        return storage
    }
}