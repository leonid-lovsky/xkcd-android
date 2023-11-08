package com.example.xkcd_android

import android.content.Context
import androidx.room.Room

class RoomDependency(applicationContext: Context) {

    private val database = Room.databaseBuilder(
        applicationContext, MainDatabase::class.java, "Comic RoomDatabase"
    ).build()

    private val service = database.service()
    private val mapper = RoomMapper()

    private val dataSource = RoomDataSource(service, mapper)

    fun dataSource() = dataSource
}