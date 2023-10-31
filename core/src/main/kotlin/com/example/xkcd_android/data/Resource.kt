package com.example.xkcd_android.data

interface Resource<T> {
    fun loading()
    fun success(value: T)
    fun massage(value: String)
    fun failure(value: Throwable)
}