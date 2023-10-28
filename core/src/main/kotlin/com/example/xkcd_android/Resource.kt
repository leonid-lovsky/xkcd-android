package com.example.xkcd_android

data class Resource<out T>(
    val status: Status,
    val data: T?,
)