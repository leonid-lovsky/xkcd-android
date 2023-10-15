package com.example.core

class Controller(private val repository: Repository) {
    fun current() {
        repository.current()
    }

    fun random() {}
    fun select() {}
    fun first() {}
    fun latest() {}
    fun previous() {}
    fun next() {}
}