package com.example.xkcd_android.data

data class Resource<T>(
    val loading: Boolean = false,
    val data: T? = null,
    val message: String? = null,
    val error: Throwable? = null,
)