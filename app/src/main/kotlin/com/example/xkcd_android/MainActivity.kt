package com.example.xkcd_android

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), Controller.Callback, View.OnClickListener {
    private var buttonCurrent: Button? = null
    private var buttonRandom: Button? = null
    private var buttonSelect: Button? = null
    private var buttonFirst: Button? = null
    private var buttonLatest: Button? = null
    private var buttonPrevious: Button? = null
    private var buttonNext: Button? = null
    private var textView: TextView? = null

    private val repository = RetrofitRepository()
    private val controller = Controller(repository)

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
        controller.callback = this
    }

    override fun onDestroy() {
        controller.callback = null
        super.onDestroy()
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.current -> controller.current()
            R.id.random -> controller.random()
            R.id.first -> controller.first()
            R.id.latest -> controller.latest()
            R.id.previous -> controller.previous()
            R.id.next -> controller.next()
            R.id.select -> {
                val selectComicDialogFragment = SelectComicDialogFragment(controller)
                selectComicDialogFragment.show(supportFragmentManager, "SELECT_COMIC_DIALOG")
            }
        }
    }

    override fun render(uiState: UIState) {
        textView?.text = uiState.toString()
    }

    override fun render(t: Throwable) {
        textView?.text = t.message
    }
}