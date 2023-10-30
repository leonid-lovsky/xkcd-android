package com.example.xkcd_android.contract

import com.example.xkcd_android.data.Comic
import com.example.xkcd_android.function.Callback
import com.example.xkcd_android.function.Resource

interface ComicRepository {
    fun comic(callback: Resource<Comic>)
    fun comic(number: Int, callback: Resource<Comic>)
    fun comic(comic: Comic)

    fun current(callback: Callback<Int>)
    fun current(number: Int)
    fun latest(callback: Callback<Int>)
    fun latest(number: Int)
}