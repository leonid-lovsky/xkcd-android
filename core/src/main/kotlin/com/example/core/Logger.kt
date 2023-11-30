package com.example.core

interface Logger {
    fun log(message: String)
    fun log(error: Throwable)
}