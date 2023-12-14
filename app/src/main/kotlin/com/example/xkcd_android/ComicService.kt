package com.example.xkcd_android

import retrofit2.http.GET
import retrofit2.http.Path

interface ComicService {

    @GET("info.0.json")
    suspend fun getLatestComic(): ComicGson

    @GET("{number}/info.0.json")
    suspend fun getComicByNumber(@Path("number") number: Int): ComicGson
}
// Retrofit retrofit = new Retrofit.Builder()
//     .baseUrl("https://api.github.com/")
//     .build();
//
// GitHubService service = retrofit.create(GitHubService.class);
