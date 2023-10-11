package com.example.core

interface Callback<T> {
    fun onSuccess(value: T)
    fun onError(message: String)
    fun onFailure(t: Throwable)
}