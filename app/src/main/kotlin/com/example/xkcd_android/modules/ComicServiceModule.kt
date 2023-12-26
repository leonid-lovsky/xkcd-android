package com.example.xkcd_android.modules

import com.example.xkcd_android.ComicConstants
import com.example.xkcd_android.ComicService
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
            .baseUrl(ComicConstants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ComicService::class.java)
    }
}
