package com.example.room

import android.content.Context
import androidx.room.Room
import com.example.core.Logger

class RoomModule(applicationContext: Context) {
    private val roomComicDatabase =
        Room.databaseBuilder(applicationContext, RoomComicDatabase::class.java, "Comic Database")
            .build()

    private val roomComicService = roomComicDatabase.roomComicService()

    fun roomComicStorage(logger: Logger): RoomComicStorage {
        return RoomComicStorage(roomComicService, logger)
    }
}