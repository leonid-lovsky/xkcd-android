package com.example.core

data class UIState(
    val first: Int = 1,
    val latest: Int = 1,
    val selected: Int = 1
) {
    val range get() = first..latest
}