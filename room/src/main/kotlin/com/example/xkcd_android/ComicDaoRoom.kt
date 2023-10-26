package com.example.xkcd_android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ComicDaoRoom {
    @Query("SELECT * FROM comic WHERE num = :num")
    fun comic(number: Int): ComicEntityRoom

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comic: ComicEntityRoom)
}