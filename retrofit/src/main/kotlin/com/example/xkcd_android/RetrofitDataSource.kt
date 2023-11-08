package com.example.xkcd_android

class RetrofitDataSource(
    private val service: RetrofitService,
    private val mapper: RetrofitMapper
) : ComicDataSource {

    override fun loadLatestComic(): ComicData? {
        val call = service.loadLatestComic()
        val response = call.execute()
        val retrofitData = response.body()
        if (retrofitData == null) return null
        return mapper.invoke2(retrofitData)
    }

    override fun loadComicByNumber(number: Int): ComicData? {
        val call = service.loadComicByNumber(number)
        val response = call.execute()
        val retrofitData = response.body()
        if (retrofitData == null) return null
        return mapper.invoke2(retrofitData)
    }

    override fun saveComic(comicData: ComicData) {
        throw NotImplementedError()
    }
}