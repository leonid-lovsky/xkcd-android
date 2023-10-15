package com.example.xkcd_android

class Controller(private val repository: Repository) : Repository.Callback {

    private var uiState = UIState()
    var screen: Screen? = null

    override fun onSuccess(comic: Comic?) {
        if (comic == null) return
        uiState = uiState.copy(comic)
        screen?.render(uiState)
    }

    override fun onFailure(t: Throwable) {}

    fun current() {
        repository.getComic(this)
    }

    fun random() {
        repository.getComic(uiState.range.random(), this)
    }

    fun select(number: Int) {
        repository.getComic(this)
    }

    fun first() {
        repository.getComic(uiState.range.first, this)
    }

    fun latest() {
        repository.getComic(uiState.range.last, this)
    }

    fun previous() {
        repository.getComic(uiState.current - 1, this)
    }

    fun next() {
        repository.getComic(uiState.current + 1, this)
    }
}