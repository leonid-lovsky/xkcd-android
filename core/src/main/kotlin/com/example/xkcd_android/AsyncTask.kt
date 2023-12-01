package com.example.xkcd_android

import java.util.concurrent.Executor

abstract class AsyncTask<T, R>(
    private val backgroundExecutor: Executor,
    private val mainThreadExecutor: Executor,
) {

    abstract fun doInBackground(parameters: T): R

    operator fun invoke(parameters: T, callback: Callback<R>) {
        backgroundExecutor.execute {
            try {
                mainThreadExecutor.execute {
                    callback.onLoading()
                }
                val value = doInBackground(parameters)
                mainThreadExecutor.execute {
                    callback.onSuccess(value)
                }
            } catch (e: Throwable) {
                mainThreadExecutor.execute {
                    callback.onFailure(e)
                }
            } finally {
                mainThreadExecutor.execute {
                    callback.onFinally()
                }
            }
        }
    }

    interface Callback<T> {

        fun onLoading()
        fun onSuccess(value: T)
        fun onFailure(e: Throwable)
        fun onFinally()
    }
}