package com.example.xkcd_android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ComicServiceRoom {
    @Query("SELECT * FROM comics WHERE num = (SELECT MAX(num) FROM comics)")
    fun comic(): ComicValueRoom?

    @Query("SELECT * FROM comics WHERE num = :number")
    fun comic(number: Int): ComicValueRoom?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun comic(comic: ComicValueRoom)
}