package com.example.xkcd_android

class ComicStorageRetrofit(
    private val comicServiceRetrofit: ComicServiceRetrofit,
    private val comicConverterRetrofit: ComicConverterRetrofit
) : ComicStorageRemote {
    override fun comic(): Comic? {
        val call = comicServiceRetrofit.comicDataRetrofit()
        val response = call.execute()
        val comicDataRetrofit = response.body()
        return comicConverterRetrofit.convert(comicDataRetrofit)
    }

    override fun comic(number: Int): Comic? {
        val call = comicServiceRetrofit.comicDataRetrofit()
        val response = call.execute()
        val comicDataRetrofit = response.body()
        return comicConverterRetrofit.convert(comicDataRetrofit)
    }
}