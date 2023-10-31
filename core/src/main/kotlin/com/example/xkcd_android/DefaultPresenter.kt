package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicPresenter
import com.example.xkcd_android.contract.ComicRepository
import com.example.xkcd_android.contract.ComicView
import com.example.xkcd_android.data.Comic
import kotlin.random.Random

class DefaultPresenter(
    private val repository: ComicRepository
) : ComicPresenter {
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
            repository.comic(this)
        }
        if (this.comicState.comic == null) {
            repository.comic(this.comicState.current, this)
        }
        if (comicState?.last == comicState?.first) {
            repository.comic(this)
        }
    }

    fun start(comicView: ComicView) {
        this.comicView = comicView
        this.comicView?.render(comicState)
    }

    fun restoreState() {
        comicView = null
    }

    fun saveState() {
        // TODO: thread
        comicPreferences.saveInstanceState(comicState)
    }

    fun restoreState() {
        // TODO: thread
        val comicState = comicPreferences.restoreInstanceState()
        if (comicState != null) {
            this.comicState = comicState
        }
    }

    fun latest() {
        repository.comic(this)
    }

    fun select() {
        comicView?.showSelectComicDialog()
    }

    fun select(number: Int) {
        repository.comic(number, this)
    }

    fun first() {
        repository.comic(comicState.first, this)
    }

    fun last() {
        repository.comic(comicState.last, this)
    }

    fun previous() {
        repository.comic(comicState.current - 1, this)
    }

    fun next() {
        repository.comic(comicState.current + 1, this)
    }

    fun refresh() {
        repository.comic(comicState.current, this)
    }

    fun random() {
        repository.comic(Random.nextInt(comicState.first, comicState.last), this)
    }
}