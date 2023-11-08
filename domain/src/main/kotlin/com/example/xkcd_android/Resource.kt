package com.example.xkcd_android

data class Resource<T>(
    val loading: Boolean = false,
    val data: T? = null,
    val error: Throwable? = null,
    val message: String? = null,
)