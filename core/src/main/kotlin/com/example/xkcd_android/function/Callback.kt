package com.example.xkcd_android.function

interface Callback<T> {
    operator fun invoke(value: T)
}