package com.example.xkcd_android

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitModule {
    private val retrofit = Retrofit.Builder().baseUrl("https://xkcd.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val service = retrofit.create(RetrofitService::class.java)

    private val callback = object : Callback<Comic> {
        override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
            presenter?.render(response.body())
        }

        override fun onFailure(call: Call<Comic>, t: Throwable) {
            presenter?.render(t)
        }
    }

    var presenter: Presenter? = null

    val repository = RetrofitRepository(
        service, callback
    )
}