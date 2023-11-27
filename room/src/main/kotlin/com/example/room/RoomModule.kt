package com.example.room

import android.content.Context
import androidx.room.Room

class RoomModule(applicationContext: Context) {
    private val mainDatabase =
        Room.databaseBuilder(applicationContext, MainDatabase::class.java, "Comic Database").build()
    private val api = mainDatabase.api()
    private val source = RoomSource(api)
    fun source() = source
}