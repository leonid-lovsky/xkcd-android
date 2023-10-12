package com.example.core

interface Repository {
    fun getComic()
    fun getComic(number: Int)
}