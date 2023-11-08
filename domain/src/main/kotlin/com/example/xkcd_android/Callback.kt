package com.example.xkcd_android

interface Callback<T> {
    operator fun invoke(value: T)
}