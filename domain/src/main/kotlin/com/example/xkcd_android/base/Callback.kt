package com.example.xkcd_android.base

interface Callback<T> {

    operator fun invoke(value: T)
}