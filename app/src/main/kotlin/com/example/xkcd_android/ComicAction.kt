package com.example.xkcd_android

sealed class ComicAction {
    data class GetComic(val number: Int) : ComicAction()
    data object RefreshComic : ComicAction()
    data object GetRandomComic : ComicAction()
    data object GetFirstComic : ComicAction()
    data object GetPreviousComic : ComicAction()
    data object GetNextComic : ComicAction()
    data object GetLatestComic : ComicAction()
    data object GetLastComic : ComicAction()
}
