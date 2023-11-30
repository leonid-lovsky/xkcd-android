package com.example.xkcd_android

class RetrofitComicStorage(private val retrofitComicService: RetrofitComicService) :
    RemoteComicStorage {
    override fun getComicByNumber(number: Int): Comic? {
        val call = retrofitComicService.getRetrofitComicByNumber(number)
        val response = call.execute()
        val body = response.body()
        return body?.toComic()
    }

    override fun getLatestComic(): Comic? {
        val call = retrofitComicService.getLatestRetrofitComic()
        val response = call.execute()
        val body = response.body()
        return body?.toComic()
    }
}