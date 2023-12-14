package com.example.xkcd_android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicDao {

    @Query("SELECT * FROM comics WHERE num = :number")
    suspend fun getComicByNumber(number: Int): Comic

    @Query("SELECT * FROM comics WHERE num = :number")
    fun getComicByNumberFlow(number: Int): Flow<Comic> // distinctUntilChanged

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putComic(vararg comic: Comic)
}
