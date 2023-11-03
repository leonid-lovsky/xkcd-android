package com.example.xkcd_android.data

interface Callback<T> {
    fun callback(value: T)
}