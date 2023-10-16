package com.example.xkcd_android

class Controller(private val repository: Repository) : Repository.Callback {

    private var uiState = UIState()
    var callback: Callback? = null

    override fun onSuccess(comic: Comic?) {
        if (comic == null) return
        uiState = uiState.copy(comic)
        callback?.render(uiState)
    }

    override fun onFailure(t: Throwable) {
        callback?.render(t)
    }

    fun current() {
        repository.getComic(this)
    }

    fun random() {
        repository.getComic(uiState.range.random(), this)
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

    interface Callback {
        fun render(uiState: UIState)
        fun render(t: Throwable)
    }
}