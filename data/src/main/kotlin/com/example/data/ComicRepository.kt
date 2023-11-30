package com.example.data

import java.util.concurrent.Executor

class ComicRepository(
    private val localComicStorage: LocalComicStorage,
    private val remoteComicStorage: RemoteComicStorage,
    private val mainThreadExecutor: Executor,
    private val backgroundExecutor: Executor,
) {
}