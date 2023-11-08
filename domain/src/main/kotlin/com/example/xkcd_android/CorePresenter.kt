package com.example.xkcd_android

import kotlin.math.max
import kotlin.random.Random

class CorePresenter(
    private val repository: ComicInteractor
) : ComicPresenter {
    // TODO: finite state machine, shared view state, use-case interactor
    private var view: ComicView? = null
    private var comicData: ComicData? = null
    private var currentNumber: Int? = null
    private var latestNumber: Int? = null

    private val currentNumberCallback = CurrentNumberCallback()
    private val latestNumberCallback = LatestNumberCallback()

    private val comicResourseCallback = ComicResourseCallback()

    override fun setView(view: ComicView?) {
        // TODO: finite state machine
        this.view = view
        val comic = this.comicData
        if (comic != null) {
            view?.render(comic)
        }
    }

    override fun restoreState() {
        // TODO: finite state machine
        repository.loadCurrentNumber(currentNumberCallback)
        repository.loadLatestNumber(latestNumberCallback)
    }

    override fun saveState() {
        // TODO: finite state machine
        val current = this.currentNumber
        if (current != null) {
            repository.saveCurrentNumber(current)
        }
        val latest = this.latestNumber
        if (latest != null) {
            repository.saveLatestNumber(latest)
        }
    }

    override fun loadLatestComic() {
        // TODO: finite state machine
        repository.loadLatestComic(comicResourseCallback)
    }

    override fun loadFirstComic() {
        // TODO: application constants
        repository.loadComicByNumber(1, comicResourseCallback)
    }

    override fun loadLastComic() {
        // TODO: finite state machine
        val latestNumber = this.latestNumber
        if (latestNumber != null) {
            repository.loadComicByNumber(latestNumber, comicResourseCallback)
        }
    }

    override fun loadPreviousComic() {
        // TODO: finite state machine
        val currentNumber = this.currentNumber
        if (currentNumber != null) {
            repository.loadComicByNumber(currentNumber - 1, comicResourseCallback)
        }
    }

    override fun loadNextComic() {
        // TODO: finite state machine
        val currentNumber = this.currentNumber
        if (currentNumber != null) {
            repository.loadComicByNumber(currentNumber + 1, comicResourseCallback)
        }
    }

    override fun refreshCurrentComic() {
        // TODO: finite state machine
        val currentNumber = this.currentNumber
        if (currentNumber != null) {
            repository.loadComicByNumber(currentNumber, comicResourseCallback)
        }
    }

    override fun displaySelectComicDialog() {
        // TODO: view responsobility
        view?.displaySelectComicDialog()
    }

    override fun loadComicByNumber(number: Int) {
        // TODO: use-case interactor
        repository.loadComicByNumber(number, comicResourseCallback)
    }

    override fun loadRandomComic() {
        // TODO: finite state machine
        val latestNumber = this.latestNumber
        if (latestNumber != null) {
            repository.loadComicByNumber(Random.nextInt(1, latestNumber), comicResourseCallback)
        }
    }

    inner class CurrentNumberCallback : Callback<Int?> {
        override fun invoke(value: Int?) {
            if (value != null) {
                repository.loadComicByNumber(value, comicResourseCallback)
            } else {
                repository.loadLatestComic(comicResourseCallback)
            }
        }
    }

    inner class LatestNumberCallback : Callback<Int?> {
        // TODO: finite state machine
        override fun invoke(value: Int?) {
            this@CorePresenter.latestNumber = value
        }
    }

    inner class ComicResourseCallback : Callback<Resource<ComicData>> {
        // TODO: check if memory leak exists
        override fun invoke(value: Resource<ComicData>) {
            if (value.loading) {
                view?.showProgress()
            } else {
                view?.hideProgress()
            }

            if (value.data != null) {
                currentNumber = value.data.num // TODO: finite state machine
                val latestNumber = this@CorePresenter.latestNumber // TODO: architecture
                if (latestNumber != null) {
                    this@CorePresenter.latestNumber = max(latestNumber, value.data.num)
                } else {
                    this@CorePresenter.latestNumber = value.data.num  // TODO: finite state machine
                }

                view?.render(value.data)
            }

            if (value.message != null) {
                view?.render(value.message)
            }

            if (value.error != null) {
                view?.render(value.error)
            }
        }
    }
}