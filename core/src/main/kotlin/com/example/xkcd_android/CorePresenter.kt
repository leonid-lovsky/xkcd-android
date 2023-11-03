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
            view?.renderComic(comic)
        }
    }

    override fun restoreState() {
        repository.loadCurrentNumber(currentNumberCallback)
        repository.loadLatestNumber(latestNumberCallback)
    }

    override fun saveState() {
        val current = this.current
        if (current != null) {
            repository.saveCurrentNumber(current)
        }
        val latest = this.latest
        if (latest != null) {
            repository.saveLatestNumber(latest)
        }
    }

    override fun loadLatestComic() {
        TODO("Not yet implemented")
    }

    override fun loadFirstComic() {
        TODO("Not yet implemented")
    }

    override fun loadLastComic() {
        TODO("Not yet implemented")
    }

    override fun loadPreviousComic() {
        TODO("Not yet implemented")
    }

    override fun loadNextComic() {
        TODO("Not yet implemented")
    }

    override fun refreshCurrentComic() {
        TODO("Not yet implemented")
    }

    override fun showSelectComicDialog() {
        TODO("Not yet implemented")
    }

    override fun loadComicByNumber(number: Int) {
        TODO("Not yet implemented")
    }

    override fun loadRandomComic() {
        TODO("Not yet implemented")
    }

    inner class CurrentNumberCallback : Callback<Int?> {
        override fun callback(value: Int?) {
            // this@CorePresenter.current = value
            if (value == null) {
                loadLatestComic()
            } else {
                loadComicByNumber(value)
            }
        }
    }

    inner class LatestNumberCallback : Callback<Int?> {
        override fun callback(value: Int?) {
            this@CorePresenter.latest = value
        }
    }
}