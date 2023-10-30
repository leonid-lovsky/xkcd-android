package com.example.xkcd_android.function

interface Resource<T> {
    fun loading()
    fun success(value: T)
    fun massage(value: String)
    fun failure(value: Throwable)
}