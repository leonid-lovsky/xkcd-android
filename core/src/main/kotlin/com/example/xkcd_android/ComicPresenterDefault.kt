package com.example.xkcd_android

import com.example.xkcd_android.function.Callback
import com.example.xkcd_android.contract.ComicPreferences
import com.example.xkcd_android.contract.ComicPresenter
import com.example.xkcd_android.contract.ComicView
import kotlin.random.Random

class ComicPresenterDefault(
    private val comicRepository: ComicRepositoryDefault,
    private val comicPreferences: ComicPreferences
) : ComicPresenter, Callback<Resource<Comic>> {
    private var comicState = ComicState()
    private var comicView: ComicView? = null

    override fun invoke(value: Resource<Comic>) {
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

    fun create() {
        val comicState = comicPreferences.restoreInstanceState()
        if (comicState == null) {
            comicRepository.comic(this)
        }
        if (this.comicState.comic == null) {
            comicRepository.comic(this.comicState.current, this)
        }
        if (comicState?.last == comicState?.first) {
            comicRepository.comic(this)
        }
    }

    fun start(comicView: ComicView) {
        this.comicView = comicView
        this.comicView?.render(comicState)
    }

    fun stop() {
        comicView = null
    }

    fun saveInstanceState() {
        // TODO: thread
        comicPreferences.saveInstanceState(comicState)
    }

    fun restoreInstanceState() {
        // TODO: thread
        val comicState = comicPreferences.restoreInstanceState()
        if (comicState != null) {
            this.comicState = comicState
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