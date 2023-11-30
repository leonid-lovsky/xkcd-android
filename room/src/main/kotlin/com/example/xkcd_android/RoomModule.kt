package com.example.xkcd_android

import android.content.Context
import androidx.room.Room

class RoomModule(applicationContext: Context) {
    private val roomComicDatabase =
        Room.databaseBuilder(applicationContext, RoomComicDatabase::class.java, "Comic Database")
            .build()

    private val roomComicService = roomComicDatabase.roomComicService()

    fun roomComicStorage(): RoomComicStorage {
        return RoomComicStorage(roomComicService)
    }
}