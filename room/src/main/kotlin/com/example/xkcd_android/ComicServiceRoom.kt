package com.example.xkcd_android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ComicServiceRoom {
    @Query("SELECT * FROM comics WHERE num = (SELECT MAX(num) FROM comics)")
    fun comicDataRoom(): ComicDataRoom?

    @Query("SELECT * FROM comics WHERE num = :number")
    fun comicDataRoom(number: Int): ComicDataRoom?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun comicDataRoom(comicDataRoom: ComicDataRoom)
}