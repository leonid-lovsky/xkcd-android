package com.example.xkcd_android

import android.content.Context
import androidx.room.Room

class RoomDependency(applicationContext: Context) {

    private val mainDatabase = Room.databaseBuilder(
        applicationContext, MainDatabase::class.java, "Comic RoomDatabase"
    ).build()

    private val roomService = mainDatabase.service()
    private val roomConverter = RoomConverter()

    private val roomDataStorage = RoomDataStorage(roomService, roomConverter)

    fun roomDataStorage() = roomDataStorage
}