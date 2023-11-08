package com.example.xkcd_android

interface BaseConverter<T, R> {

    fun invoke1(value: T): R
    fun invoke2(value: R): T
}