package com.example.domain

import com.example.core.Callback
import com.example.core.Interactor
import com.example.core.Logger
import com.example.data.Comic
import com.example.data.LocalComicStorage
import com.example.data.RemoteComicStorage

class GetComicByNumber(
    private val comicSourceLocal: LocalComicStorage,
    private val comicSourceRemote: RemoteComicStorage,
    private val logger: Logger,
) : Interactor<Int, Comic?> {
    override fun invoke(input: Int): Comic? {
        logger.log("invoke:input:$input")
        val comicLocal = comicSourceLocal.getComicByNumber(input)
        if (comicLocal != null) {
            return comicLocal
        }
        val comicRemote = comicSourceRemote.getComicByNumber(input)
        if (comicRemote != null) {
            comicSourceLocal.putComic(comicRemote)
            return comicSourceLocal.getComicByNumber(input)
        }
    }

    override fun invoke(input: Int, callback: Callback<Comic?>) {
        logger.log("invoke:input:$input:callback:$callback")
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