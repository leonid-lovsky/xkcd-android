package com.example.xkcd_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.core.Callback
import com.example.core.Comic
import com.example.core.Repository
import com.example.retrofit.RepositoryImpl

class MainActivity : AppCompatActivity() {
    private val callback = object : Callback<Comic?> {
        override fun onSuccess(value: Comic?, message: String) {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }

        override fun onFailure(t: Throwable) {
            Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
        }
    }
    private val repository: Repository = RepositoryImpl(callback)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository.requestComic()
    }
}