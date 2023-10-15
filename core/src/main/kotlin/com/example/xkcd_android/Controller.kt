package com.example.xkcd_android

class Controller(private val repository: Repository<Comic>) {
    private val callback = object: Callback<Comic> {
        override fun onSuccess(value: Comic) {
            TODO("Not yet implemented")
        }

        override fun onFailure(t: Throwable) {
            TODO("Not yet implemented")
        }
    }

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