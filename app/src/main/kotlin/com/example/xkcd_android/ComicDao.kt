package com.example.xkcd_android

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicDao {

    @Query("SELECT * FROM comics ORDER BY num DESC LIMIT 1")
    suspend fun getLatestComic(): Comic?

    @Query("SELECT * FROM comics ORDER BY num DESC LIMIT 1")
    fun getLatestComicLiveData(): LiveData<Comic> // distinctUntilChanged

    @Query("SELECT * FROM comics ORDER BY num DESC LIMIT 1")
    fun getLatestComicFlow(): Flow<Comic> // distinctUntilChanged

    @Query("SELECT * FROM comics WHERE num = :number")
    suspend fun getComic(number: Int): Comic?

    @Query("SELECT * FROM comics WHERE num = :number")
    fun getComicLiveData(number: Int): LiveData<Comic> // distinctUntilChanged

    @Query("SELECT * FROM comics WHERE num = :number")
    fun getComicFlow(number: Int): Flow<Comic> // distinctUntilChanged

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putComic(vararg comic: Comic): List<Long>
}
