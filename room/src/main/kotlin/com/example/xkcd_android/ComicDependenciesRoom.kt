package com.example.xkcd_android

import android.content.Context
import androidx.room.Room

class ComicDependenciesRoom(applicationContext: Context) : ComicDependenciesLocal {
    private val database = Room.databaseBuilder(
        applicationContext,
        ComicDatabaseRoom::class.java, "database"
    ).build()

    private val comicDaoRoom = database.comicDaoRoom()

    override fun comicStorageLocal(): ComicStorageLocal {
        return ComicStorageRoom(comicDaoRoom)
    }
}