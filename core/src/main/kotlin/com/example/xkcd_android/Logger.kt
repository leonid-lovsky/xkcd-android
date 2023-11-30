package com.example.xkcd_android

interface Logger {
    fun log(message: String)
    fun log(error: Throwable)
}