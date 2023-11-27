package com.example.core

interface Interactor<I, O> {
    operator fun invoke(input: I): O
    operator fun invoke(input: I, callback: Callback<O>)
}