package com.example.xkcd_android

import com.example.xkcd_android.contract.ComicPresenter
import com.example.xkcd_android.contract.ComicRepository
import com.example.xkcd_android.contract.ComicView
import com.example.xkcd_android.data.Callback
import com.example.xkcd_android.data.Comic

class CorePresenter(
    private val repository: ComicRepository
) : ComicPresenter {
    private var view: ComicView? = null
    private var comic: Comic? = null
    private var current: Int? = null
    private var latest: Int? = null

    private val currentNumberCallback = CurrentNumberCallback()
    private val latestNumberCallback = LatestNumberCallback()

    override fun setView(view: ComicView?) {
        this.view = view
        val comic = this.comic
        if (comic != null) {
            view?.render(comic)
        }
    }

    override fun restoreState() {
        repository.current(currentNumberCallback)
        repository.latest(latestNumberCallback)
    }

    override fun saveState() {
        val current = this.current
        if (current != null) {
            repository.current(current)
        }
        val latest = this.latest
        if (latest != null) {
            repository.latest(latest)
        }
    }

    override fun latest() {
        TODO("Not yet implemented")
    }

    override fun first() {
        TODO("Not yet implemented")
    }

    override fun last() {
        TODO("Not yet implemented")
    }

    override fun previous() {
        TODO("Not yet implemented")
    }

    override fun next() {
        TODO("Not yet implemented")
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }

    override fun select() {
        TODO("Not yet implemented")
    }

    override fun select(number: Int) {
        TODO("Not yet implemented")
    }

    override fun random() {
        TODO("Not yet implemented")
    }

    inner class CurrentNumberCallback : Callback<Int> {
        override fun invoke(value: Int) {
            this@CorePresenter.current = value
        }
    }

    inner class LatestNumberCallback : Callback<Int> {
        override fun invoke(value: Int) {
            this@CorePresenter.latest = value
        }
    }

    // override fun invoke(value: Resource<Comic>) {
    //     if (value.loading) {
    //         view?.showProgress()
    //     } else {
    //         view?.hideProgress()
    //     }
    //     val comic = value.data
    //     if (comic != null) {
    //         comicState = comicState.copy(comic)
    //         view?.render(comicState)
    //     }
    //     val error = value.error
    //     if (error != null) {
    //         view?.render(error)
    //     }
    // }
    //
    // fun create() {
    //     val comicState = comicPreferences.restoreInstanceState()
    //     if (comicState == null) {
    //         repository.comic(this)
    //     }
    //     if (this.comicState.comic == null) {
    //         repository.comic(this.comicState.current, this)
    //     }
    //     if (comicState?.last == comicState?.first) {
    //         repository.comic(this)
    //     }
    // }
    //
    // fun start(comicView: ComicView) {
    //     this.view = comicView
    //     this.view?.render(comicState)
    // }
    //
    // fun start() {
    //     view = null
    // }
    //
    // fun stop() {
    //     // TODO: thread
    //     comicPreferences.saveInstanceState(comicState)
    // }
    //
    // fun restoreState() {
    //     // TODO: thread
    //     val comicState = comicPreferences.restoreInstanceState()
    //     if (comicState != null) {
    //         this.comicState = comicState
    //     }
    // }
    //
    // fun latest() {
    //     repository.comic(this)
    // }
    //
    // fun select() {
    //     view?.showSelectComicDialog()
    // }
    //
    // fun select(number: Int) {
    //     repository.comic(number, this)
    // }
    //
    // fun first() {
    //     repository.comic(comicState.first, this)
    // }
    //
    // fun last() {
    //     repository.comic(comicState.last, this)
    // }
    //
    // fun previous() {
    //     repository.comic(comicState.current - 1, this)
    // }
    //
    // fun next() {
    //     repository.comic(comicState.current + 1, this)
    // }
    //
    // fun refresh() {
    //     repository.comic(comicState.current, this)
    // }
    //
    // fun random() {
    //     repository.comic(Random.nextInt(comicState.first, comicState.last), this)
    // }
}