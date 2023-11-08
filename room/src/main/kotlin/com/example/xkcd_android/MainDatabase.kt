package com.example.xkcd_android

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomData::class], version = 1)
abstract class MainDatabase : RoomDatabase() {

    abstract fun service(): RoomService
}