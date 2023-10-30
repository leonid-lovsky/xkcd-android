package com.example.xkcd_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("info.0.json")
    operator fun invoke(): Call<RetrofitData>

    @GET("{number}/info.0.json")
    operator fun invoke(@Path("number") number: Int): Call<RetrofitData>
}