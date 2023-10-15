package com.example.xkcd_android

import retrofit2.Callback

class RetrofitRepository(
    private val service: RetrofitService,
    private val callback: Callback<Comic>,
) : Repository {
    override fun getComic() {
        service.getComic().enqueue(callback)
    }

    override fun getComic(number: Int) {
        service.getComic(number).enqueue(callback)
    }
}