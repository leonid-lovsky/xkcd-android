package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomComic::class], version = 1)
abstract class MainDatabase : RoomDatabase() {
    abstract fun api(): RoomAPI
}