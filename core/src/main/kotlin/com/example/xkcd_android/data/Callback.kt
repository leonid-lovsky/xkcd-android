package com.example.xkcd_android.data

interface Callback<T> {
    operator fun invoke(value: T)
}