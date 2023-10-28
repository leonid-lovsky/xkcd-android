package com.example.xkcd_android

import android.content.Context
import androidx.room.Room

class ComicModuleRoom(applicationContext: Context) : ComicModuleLocal {
    private val database = Room.databaseBuilder(
        applicationContext,
        ComicDatabaseRoom::class.java, "database"
    ).build()

    private val comicServiceRoom = database.comicServiceRoom()
    private val comicConverterRoom = ComicConverterRoomDefault()
    private val comicStorageRoom = ComicStorageRoom(comicServiceRoom, comicConverterRoom)

    override fun comicStorageLocal(): ComicStorageLocal {
        return comicStorageRoom
    }
}