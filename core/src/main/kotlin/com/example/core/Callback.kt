package com.example.core

interface Callback<T> {
    fun onSuccess(value: T, message: String)
    fun onFailure(t: Throwable)
}