package com.example.core

interface Repository {
    fun requestComic()
    fun requestComic(number: Int)
}