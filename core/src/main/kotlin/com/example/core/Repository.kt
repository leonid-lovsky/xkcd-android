package com.example.core

interface Repository {
    fun current(callback: Callback<Comic>)
    fun number(value: Int, callback: Callback<Comic>)
}