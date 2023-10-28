package com.example.xkcd_android

data class Resource<out T>(
    val loading: Boolean,
    val data: T?,
)