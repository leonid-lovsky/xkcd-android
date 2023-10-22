package com.example.xkcd_android

class ComicRepository(
    private val comicStorageLocal: ComicStorageLocal,
    private val comicStorageRemote: ComicStorageRemote,
) : ComicStorage {
    override fun getComic(callback: ComicCallback) {
        comicStorageRemote.getComic(object : ComicCallback {
            override fun onResponse(comic: Comic?) {
                comicStorageLocal.save(comic)
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })

        comicStorageLocal.getComic(object : ComicCallback {
            override fun onResponse(comic: Comic?) {
                callback.onResponse(comic)
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    override fun getComic(number: Int, callback: ComicCallback) {
        comicStorageRemote.getComic(number, object : ComicCallback {
            override fun onResponse(comic: Comic?) {
                comicStorageLocal.save(comic)
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })

        comicStorageLocal.getComic(number, object : ComicCallback {
            override fun onResponse(comic: Comic?) {
                callback.onResponse(comic)
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}