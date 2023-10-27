package com.example.xkcd_android

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ComicValueRoom::class], version = 1)
abstract class ComicDatabaseRoom : RoomDatabase() {
    abstract fun comicServiceRoom(): ComicServiceRoom
}