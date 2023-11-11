package com.example.xkcd_android

import android.content.Context
import androidx.room.Room

class RoomModule(applicationContext: Context) {

    private val mainDatabase = Room.databaseBuilder(
        applicationContext, MainDatabase::class.java, "Comic Database"
    ).build()

    private val roomService = mainDatabase.service()
    private val roomConverter = RoomConverter()

    private val roomStorage = RoomStorage(roomService, roomConverter)

    fun roomStorage() = roomStorage
}