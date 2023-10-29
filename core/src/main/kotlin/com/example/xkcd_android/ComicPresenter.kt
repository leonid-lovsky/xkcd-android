package com.example.xkcd_android

import kotlin.random.Random

class ComicPresenter(private val comicRepository: ComicRepository) : Callback<Resource<Comic>> {
    private var comicState = ComicState()
    var comicView: ComicView? = null

    override fun onChanged(value: Resource<Comic>) {
        if (value.loading) {
            comicView?.showProgress()
        } else {
            comicView?.hideProgress()
        }
        val comic = value.data
        if (comic != null) {
            comicState = comicState.copy(comic)
            comicView?.render(comicState)
        }
        val error = value.error
        if (error != null) {
            comicView?.render(error)
        }
    }

    fun init() {
        if (comicState.comic == null) {
            comicRepository.comic(comicState.current, this)
        } else {
            comicView?.render(comicState)
        }
    }

    fun latest() {
        comicRepository.comic(this)
    }

    fun select() {
        comicView?.showComicSelectDialog()
    }

    fun select(number: Int) {
        comicRepository.comic(number, this)
    }

    fun first() {
        comicRepository.comic(comicState.first, this)
    }

    fun last() {
        comicRepository.comic(comicState.last, this)
    }

    fun previous() {
        comicRepository.comic(comicState.current - 1, this)
    }

    fun next() {
        comicRepository.comic(comicState.current + 1, this)
    }

    fun refresh() {
        comicRepository.comic(comicState.current, this)
    }

    fun random() {
        comicRepository.comic(Random.nextInt(comicState.first, comicState.last), this)
    }
}