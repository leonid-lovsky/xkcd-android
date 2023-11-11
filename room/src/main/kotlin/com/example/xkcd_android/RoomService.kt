package com.example.xkcd_android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomService {

    @Query("SELECT * FROM comic WHERE num = :number")
    fun loadComicByNumber(number: Int): RoomComic?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveComic(value: RoomComic)
}