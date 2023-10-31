package com.example.xkcd_android.data

interface Converter<T, R> {
    operator fun invoke(value: T): R
    operator fun invoke(value: R): T
}