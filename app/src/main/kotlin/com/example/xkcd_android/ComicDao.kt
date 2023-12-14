package com.example.xkcd_android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComics(vararg comics: Comic)

    @Query("SELECT * FROM comics WHERE num = :number")
    suspend fun loadComicByNumber(number: Int): Comic

    @Query("SELECT * FROM comics WHERE num = :number")
    fun loadComicByNumberFlow(number: Int): Flow<Comic> // distinctUntilChanged
}
