package com.example.xkcd_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var buttonCurrent: Button? = null
    private var buttonRandom: Button? = null
    private var buttonSelect: Button? = null
    private var buttonFirst: Button? = null
    private var buttonLatest: Button? = null
    private var buttonPrevious: Button? = null
    private var buttonNext: Button? = null
    private var textView: TextView? = null

    private val callback = object : Callback<Comic?> {
        override fun onSuccess(value: Comic?, message: String) {
            if (value != null) {
                comicNumberLatest = maxOf(value.num, comicNumberLatest)
                comicNumberSelected = value.num
            }
            textView?.text =
                "first: " + comicNumberFirst + " " + "latest: " + comicNumberLatest + " " + "range: " + comicNumberRange + "\n" + value.toString() + "\n" + message
        }

        override fun onFailure(t: Throwable) {
            textView?.text = t.message
        }
    }

    private val repository: Repository = RetrofitRepository(callback)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonCurrent = findViewById(R.id.current)
        buttonRandom = findViewById(R.id.random)
        buttonSelect = findViewById(R.id.select)
        buttonFirst = findViewById(R.id.first)
        buttonLatest = findViewById(R.id.latest)
        buttonPrevious = findViewById(R.id.previous)
        buttonNext = findViewById(R.id.next)
        textView = findViewById(R.id.text_view)
        buttonCurrent?.setOnClickListener(this)
        buttonRandom?.setOnClickListener(this)
        buttonSelect?.setOnClickListener(this)
        buttonFirst?.setOnClickListener(this)
        buttonLatest?.setOnClickListener(this)
        buttonPrevious?.setOnClickListener(this)
        buttonNext?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.current -> repository.request()
            R.id.random -> repository.request(comicNumberRange.random())
            R.id.select -> {
                val selectComicDialogCallback = object : SelectComicDialogCallback {
                    override fun onSelect(number: Int) {
                        repository.request(number)
                    }
                }
                val selectComicDialogFragment = SelectComicDialogFragment(selectComicDialogCallback)
                selectComicDialogFragment.show(supportFragmentManager, "SELECT_COMIC_DIALOG")
            }

            R.id.first -> repository.request(comicNumberFirst)
            R.id.latest -> repository.request(comicNumberLatest)
            R.id.previous -> repository.request(comicNumberSelected - 1)
            R.id.next -> repository.request(comicNumberSelected + 1)
        }
    }
}