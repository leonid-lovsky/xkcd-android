package com.example.domain

import com.example.core.Callback
import com.example.core.Interactor
import com.example.core.Logger
import com.example.data.Comic
import com.example.data.LocalComicStorage
import com.example.data.RemoteComicStorage

class GetComicByNumber(
    private val logger: Logger,
    private val comicSourceLocal: LocalComicStorage,
    private val comicSourceRemote: RemoteComicStorage
) : Interactor<Int, Comic?> {
    override fun invoke(input: Int): Comic? {
        TODO("Not yet implemented")
    }

    override fun invoke(input: Int, callback: Callback<Comic?>) {
        logger.log("interactor: ${this::class.java}")
        logger.log("parameters: ${input}")
        val comicLocal = comicSourceLocal.getComicByNumber(input)
        if (comicLocal != null) {
            callback(comicLocal)
        } else {
            val comicRemote = comicSourceRemote.getComicByNumber(input)
            if (comicRemote != null) {
                comicSourceLocal.putComic(comicRemote)
                val comicLocal = comicSourceLocal.getComicByNumber(input)
                callback(comicLocal)
            }
        }
    }
}