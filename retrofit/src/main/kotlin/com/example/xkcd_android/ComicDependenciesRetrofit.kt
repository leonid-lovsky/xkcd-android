package com.example.xkcd_android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComicDependenciesRetrofit : ComicDependenciesRemote {
    private val baseUrl = "https://xkcd.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val comicServiceRetrofit = retrofit.create(ComicServiceRetrofit::class.java)
    private val comicConverterRetrofit = ComicConverterRetrofitDefault(baseUrl)
    private val comicStorageRetrofit = ComicStorageRetrofit(comicServiceRetrofit, comicConverterRetrofit)

    override fun comicStorageRemote(): ComicStorageRemote {
        return comicStorageRetrofit
    }
}