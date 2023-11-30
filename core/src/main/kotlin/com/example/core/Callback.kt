package com.example.core

interface Callback<T> {
    operator fun invoke(value: T)
}