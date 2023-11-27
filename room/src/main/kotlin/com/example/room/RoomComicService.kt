package com.example.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomComicService {
    @Query("SELECT * FROM comic WHERE num = :number")
    fun getRoomComicByNumber(number: Int): RoomComic?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun putRoomComic(value: RoomComic)
}