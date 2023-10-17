package com.example.xkcd_android

class Controller(private val repository: Repository) : Repository.Callback {

    private var uiState = UIState()
    var presenter: Presenter? = null

    override fun onSuccess(comic: Comic?) {
        if (comic == null) return
        uiState = uiState.copy(comic)
        presenter?.render(uiState)
    }

    override fun onFailure(t: Throwable) {
        presenter?.render(t)
    }

    fun current() {
        repository.getComic(this)
    }

    fun random() {
        repository.getComic(uiState.range.random(), this)
    }

    fun select() {
        presenter?.showSelectComicDialog()
    }

    fun select(number: Int) {
        repository.getComic(number, this)
    }

    fun first() {
        repository.getComic(uiState.first, this)
    }

    fun last() {
        repository.getComic(uiState.last, this)
    }

    fun previous() {
        repository.getComic(uiState.current - 1, this)
    }

    fun next() {
        repository.getComic(uiState.current + 1, this)
    }

}