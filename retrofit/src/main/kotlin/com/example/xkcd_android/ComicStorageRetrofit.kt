package com.example.xkcd_android

class ComicStorageRetrofit(
    private val comicServiceRetrofit: ComicServiceRetrofit,
    private val comicConverterRetrofit: ComicConverterRetrofit
) : ComicStorageRemote {
    override fun comic(): Comic? {
        val call = comicServiceRetrofit.comic()
        val response = call.execute()
        val comicDataRetrofit = response.body()
        if (comicDataRetrofit == null) return null
        return comicConverterRetrofit.from(comicDataRetrofit)
    }

    override fun comic(number: Int): Comic? {
        val call = comicServiceRetrofit.comic()
        val response = call.execute()
        val comicDataRetrofit = response.body()
        if (comicDataRetrofit == null) return null
        return comicConverterRetrofit.from(comicDataRetrofit)
    }
}