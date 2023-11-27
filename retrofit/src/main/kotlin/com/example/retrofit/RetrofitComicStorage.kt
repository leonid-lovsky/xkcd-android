package com.example.retrofit

import com.example.core.Logger
import com.example.data.RemoteComicStorage
import com.example.data.Comic

class RetrofitComicStorage(
    private val baseUrl: String,
    private val retrofitComicService: RetrofitComicService,
    private val logger: Logger,
) : RemoteComicStorage {
    override fun getLatestComic(): Comic? {
        val call = retrofitComicService.getLatestRetrofitComic()
        logger.log("getLatestComic:call$call")
        val response = call.execute()
        logger.log("getLatestComic:response$response")
        logger.log("getLatestComic:response.isSuccessful${response.isSuccessful()}")
        logger.log("getLatestComic:response.code${response.code()}")
        logger.log("getLatestComic:response.message${response.message()}")
        val body = response.body()
        logger.log("getLatestComic:body$body")
        val copy = body?.copy(link = baseUrl + body.num)
        logger.log("getLatestComic:copy$copy")
        return copy?.toComic()
    }

    override fun getComicByNumber(number: Int): Comic? {
        val call = retrofitComicService.getRetrofitComicByNumber(number)
        logger.log("getLatestComic:call$call")
        val response = call.execute()
        logger.log("getLatestComic:response$response")
        logger.log("getLatestComic:response.isSuccessful${response.isSuccessful()}")
        logger.log("getLatestComic:response.code${response.code()}")
        logger.log("getLatestComic:response.message${response.message()}")
        val body = response.body()
        logger.log("getLatestComic:body$body")
        val copy = body?.copy(link = baseUrl + body.num)
        logger.log("getLatestComic:copy$copy")
        return copy?.toComic()
    }
}