package com.example.xkcd_android

interface BaseCallback<T> {

    operator fun invoke(value: T)
}