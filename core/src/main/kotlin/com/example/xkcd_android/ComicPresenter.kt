package com.example.xkcd_android

import kotlin.random.Random

class ComicPresenter(private val comicRepository: ComicRepository) : Callback<Resource<Comic>> {
    private var comicPage = ComicPage()
    var comicView: ComicView? = null

    override fun onChanged(value: Resource<Comic>) {
        if (value.loading) {
            comicView?.showProgress()
        } else {
            comicView?.hideProgress()
        }
        val comic = value.data
        if (comic != null) {
            comicPage = comicPage.copy(comic)
            comicView?.render(comicPage)
        }
        val error = value.error
        if (error != null) {
            comicView?.render(error)
        }
    }

    fun init() {
        if (comicPage.comic == null) {
            comicRepository.comic(comicPage.current, this)
        } else {
            comicView?.render(comicPage)
        }
    }

    fun current() {
        comicRepository.comic(this)
    }

    fun select() {
        comicView?.showComicSelectDialog()
    }

    fun select(number: Int) {
        comicRepository.comic(number, this)
    }

    fun first() {
        comicRepository.comic(comicPage.first, this)
    }

    fun last() {
        comicRepository.comic(comicPage.last, this)
    }

    fun previous() {
        comicRepository.comic(comicPage.current - 1, this)
    }

    fun next() {
        comicRepository.comic(comicPage.current + 1, this)
    }

    fun refresh() {
        comicRepository.comic(comicPage.current, this)
    }

    fun random() {
        comicRepository.comic(Random.nextInt(comicPage.first, comicPage.last), this)
    }
}