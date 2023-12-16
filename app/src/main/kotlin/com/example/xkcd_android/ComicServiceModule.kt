package com.example.xkcd_android

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ComicServiceModule {

    @Provides
    fun provideComicService(
    ): ComicService {
        return Retrofit.Builder()
            .baseUrl("https://xkcd.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ComicService::class.java)
    }
}
