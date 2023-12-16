package com.example.xkcd_android

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ComicDatabaseModule {

    @Provides
    fun provideComicDao(
        comicDatabase: ComicDatabase
    ): ComicDao {
        return comicDatabase.comicDao()
    }

    @Provides
    @Singleton
    fun provideComicDatabase(
        @ApplicationContext context: Context
    ): ComicDatabase {
        return Room.databaseBuilder(
            context,
            ComicDatabase::class.java,
            "comic_database"
        ).build()
    }
}
