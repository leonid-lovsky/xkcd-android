package com.example.xkcd_android.data

interface Converter<T, R> {
    fun convert(value: T): R
    fun convetr(value: R): T
}