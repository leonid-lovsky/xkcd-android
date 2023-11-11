package com.example.xkcd_android

class RetrofitStorage(
    private val retrofitService: RetrofitService,
    private val retrofitConverter: RetrofitConverter,
) : ComicStorage.Remote {

    override fun loadLatestComic(): Comic? {
        val call = retrofitService.loadLatestComic()
        val response = call.execute()
        val body = response.body()
        if (body == null) return null
        return retrofitConverter.convert(body)
    }

    override fun loadComicByNumber(number: Int): Comic? {
        val call = retrofitService.loadComicByNumber(number)
        val response = call.execute()
        val body = response.body()
        if (body == null) return null
        return retrofitConverter.convert(body)
    }
}