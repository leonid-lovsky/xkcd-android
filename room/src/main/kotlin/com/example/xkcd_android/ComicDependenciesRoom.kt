package com.example.xkcd_android

import android.content.Context
import androidx.room.Room

class ComicDependenciesRoom(applicationContext: Context) : ComicDependenciesLocal {
    private val database = Room.databaseBuilder(
        applicationContext,
        ComicDatabaseRoom::class.java, "database"
    ).build()

    private val comicDaoRoom = database.comicDaoRoom()
    private val comicConverterRetrofit = ComicConverterRoomDefault()
    private val comicStorageRoom = ComicStorageRoom(comicDaoRoom, comicConverterRetrofit)

    override fun comicStorageLocal(): ComicStorageLocal {
        return comicStorageRoom
    }
}