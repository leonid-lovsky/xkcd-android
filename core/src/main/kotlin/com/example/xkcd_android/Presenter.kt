package com.example.xkcd_android

interface Presenter {
    fun render(uiState: UIState)
    fun render(t: Throwable)
    fun showSelectComicDialog()
}