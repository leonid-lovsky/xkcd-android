package com.example.xkcd_android

class RetrofitDataStorage(
    private val retrofitService: RetrofitService,
    private val retrofitConverter: RetrofitConverter
) : ComicDataStorage {

    override fun loadLatestComic(): ComicData? {
        val call = retrofitService.loadLatestComic()
        val response = call.execute()
        val retrofitData = response.body()
        if (retrofitData == null) return null
        return retrofitConverter.invoke2(retrofitData)
    }

    override fun loadComicByNumber(number: Int): ComicData? {
        val call = retrofitService.loadComicByNumber(number)
        val response = call.execute()
        val retrofitData = response.body()
        if (retrofitData == null) return null
        return retrofitConverter.invoke2(retrofitData)
    }

    override fun saveComic(comicData: ComicData) {
        throw NotImplementedError()
    }
}