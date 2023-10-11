package com.example.core

interface Repository {
    fun getComic(number: Int?, callback: Callback<Comic>)
}