package com.example.xkcd_android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomService {
    @Query("SELECT * FROM comic WHERE num = (SELECT MAX(num) FROM comic)")
    operator fun invoke(): RoomData?

    @Query("SELECT * FROM comic WHERE num = :number")
    operator fun invoke(number: Int): RoomData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    operator fun invoke(value: RoomData)
}