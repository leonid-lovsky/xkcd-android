package com.example.core

interface Logger {
    fun message(message: String)
    fun message(error: Throwable)
}