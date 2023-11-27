package com.example.domain

import com.example.core.Callback
import com.example.core.Interactor
import com.example.core.Logger
import com.example.data.Comic
import com.example.data.LocalComicStorage
import com.example.data.RemoteComicStorage
import java.util.concurrent.Executor

class GetComicByNumber(
    private val mainThreadExecutor: Executor,
    private val backgroundExecutor: Executor,
    private val comicSourceLocal: LocalComicStorage,
    private val comicSourceRemote: RemoteComicStorage,
    private val logger: Logger,
) : Interactor<Int, Comic?> {
    override fun invoke(input: Int): Comic? {
        logger.log("invoke:input:$input")
        val comicLocal = comicSourceLocal.getComicByNumber(input)
        logger.log("invoke:comicLocal:$comicLocal")
        if (comicLocal != null) {
            return comicLocal
        }
        val comicRemote = comicSourceRemote.getComicByNumber(input)
        logger.log("invoke:comicRemote:$comicRemote")
        if (comicRemote != null) {
            comicSourceLocal.putComic(comicRemote)
        }
        val result = comicSourceLocal.getComicByNumber(input)
        logger.log("invoke:result:$result")
        return result
    }

    override fun invoke(input: Int, callback: Callback<Comic?>) {
        logger.log("invoke:input:$input:callback:$callback")
        backgroundExecutor.execute {
            val result = invoke(input)
            logger.log("invoke:result:$result")
            mainThreadExecutor.execute {
                callback(result)
            }
        }
    }
}