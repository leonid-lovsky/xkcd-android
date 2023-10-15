package com.example.core

interface Callback<T> {
    fun onSuccess(value: T)
    fun onFailure(t: Throwable)
}