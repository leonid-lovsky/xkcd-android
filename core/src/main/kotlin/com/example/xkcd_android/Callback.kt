package com.example.xkcd_android

interface Callback<T> {
    fun onChanged(value: T)
}