package com.example.core

interface Callback<O> {
    operator fun invoke(output: O)
}