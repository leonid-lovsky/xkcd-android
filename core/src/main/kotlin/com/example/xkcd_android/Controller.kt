package com.example.xkcd_android

class Controller(private val interactor: Interactor) : Interactor.Callback {

    private var state = State()
    var presenter: Presenter? = null

    override fun onSuccess(comic: Comic?) {
        if (comic == null) return
        state = state.copy(comic)
        presenter?.render(state)
    }

    override fun onFailure(t: Throwable) {
        presenter?.render(t)
    }

    fun current() {
        interactor.getComic(this)
    }

    fun random() {
        interactor.getComic(state.range.random(), this)
    }

    fun select() {
        presenter?.showSelectComicDialog()
    }

    fun select(number: Int) {
        interactor.getComic(number, this)
    }

    fun first() {
        interactor.getComic(state.first, this)
    }

    fun last() {
        interactor.getComic(state.last, this)
    }

    fun previous() {
        interactor.getComic(state.current - 1, this)
    }

    fun next() {
        interactor.getComic(state.current + 1, this)
    }

}