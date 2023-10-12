package com.example.xkcd_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.core.Callback
import com.example.core.Comic
import com.example.core.Repository
import com.example.retrofit.RepositoryImpl
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {
    private var buttonCurrent: Button? = null
    private var buttonRandom: Button? = null
    private var buttonSelect: Button? = null
    private var buttonFirst: Button? = null
    private var buttonLatest: Button? = null

    private var textView: TextView? = null

    private val callback = object : Callback<Comic?> {
        override fun onSuccess(value: Comic?, message: String) {
            textView?.text = value.toString() + message
        }

        override fun onFailure(t: Throwable) {
            textView?.text = t.message
        }
    }

    private val repository: Repository = RepositoryImpl(callback)

    private val comicNumberFirst = 1
    private var comicNumberLatest = 1

    private val comicNumberRange = comicNumberFirst..comicNumberLatest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCurrent = findViewById(R.id.current)
        buttonRandom = findViewById(R.id.random)
        buttonSelect = findViewById(R.id.select)
        buttonFirst = findViewById(R.id.first)
        buttonLatest = findViewById(R.id.latest)

        textView = findViewById(R.id.text_view)

        buttonCurrent?.setOnClickListener { repository.requestComic() }
        buttonRandom?.setOnClickListener { repository.requestComic(comicNumberRange.random()) }
        buttonSelect?.setOnClickListener { repository.requestComic(5) }
        buttonFirst?.setOnClickListener { repository.requestComic(comicNumberFirst) }
        buttonLatest?.setOnClickListener { repository.requestComic(comicNumberLatest) }
    }
}