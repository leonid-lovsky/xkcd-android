package com.example.xkcd_android

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicDao {

    @Query("SELECT * FROM comics WHERE num = :comicNumber")
    suspend fun getComicByNumber(comicNumber: Int): Comic?

    @Query("SELECT * FROM comics WHERE num = :comicNumber")
    fun getComicLiveDataByNumber(comicNumber: Int): LiveData<Comic> // distinctUntilChanged

    @Query("SELECT * FROM comics WHERE num = :comicNumber")
    fun getComicFlow(comicNumber: Int): Flow<Comic> // distinctUntilChanged

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putComic(vararg comic: Comic): List<Long>
}
