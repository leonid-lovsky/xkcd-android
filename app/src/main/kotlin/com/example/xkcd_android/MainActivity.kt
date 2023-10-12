package com.example.xkcd_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.core.Callback
import com.example.core.Comic
import com.example.core.Repository
import com.example.retrofit.RepositoryImpl

class MainActivity : AppCompatActivity() {
    private val callback = object : Callback<Comic?> {
        override fun onSuccess(value: Comic?, message: String) {}

        override fun onFailure(t: Throwable) {}
    }
    private val repository: Repository = RepositoryImpl(callback)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository.getComic()
    }
}