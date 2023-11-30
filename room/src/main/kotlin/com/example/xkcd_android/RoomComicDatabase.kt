package com.example.xkcd_android

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomComic::class], version = 1)
abstract class RoomComicDatabase : RoomDatabase() {

    abstract fun roomComicService(): RoomComicService
}