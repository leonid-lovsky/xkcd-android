package com.example.xkcd_android.data

interface Converter<T, R> {
    fun convert(value: T): R
    fun convert(value: R): T
}