package com.example.xkcd_android

interface Callback<T> {
    fun onSuccess(value: T)
    fun onFailure(t: Throwable)
}