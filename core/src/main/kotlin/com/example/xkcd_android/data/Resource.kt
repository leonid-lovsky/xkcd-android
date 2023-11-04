package com.example.xkcd_android.data

data class Resource<T>(
    val loading: Boolean,
    val data: T?,
    val message: String?,
    val error: Throwable?,
)