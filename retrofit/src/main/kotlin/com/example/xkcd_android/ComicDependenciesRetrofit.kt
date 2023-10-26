package com.example.xkcd_android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComicDependenciesRetrofit : ComicDependenciesRemote {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://xkcd.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val comicServiceRetrofit = retrofit.create(ComicServiceRetrofit::class.java)
    private val comicStorageRetrofit = ComicStorageRetrofit(comicServiceRetrofit)

    override fun comicStorageRemote(): ComicStorageRemote {
        return comicStorageRetrofit
    }
}