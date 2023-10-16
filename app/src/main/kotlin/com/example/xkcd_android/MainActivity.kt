package com.example.xkcd_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity(), Controller.Callback, View.OnClickListener {
    private var comicTitle: TextView? = null
    private var comicImage: ImageView? = null
    private var comicText: TextView? = null
    private var comicUrlTextView: TextView? = null
    private var imageUrlTextView: TextView? = null

    private var buttonCurrent: Button? = null
    private var buttonRandom: Button? = null
    private var buttonSelect: Button? = null
    private var buttonFirst: Button? = null
    private var buttonLatest: Button? = null
    private var buttonPrevious: Button? = null
    private var buttonNext: Button? = null

    private val repository = RetrofitRepository()
    private val controller = Controller(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        comicImage = findViewById(R.id.comic_image)
        comicText = findViewById(R.id.comic_transcript)
        comicUrlTextView = findViewById(R.id.comic_link)
        imageUrlTextView = findViewById(R.id.image_url)
        buttonCurrent = findViewById(R.id.current)
        buttonRandom = findViewById(R.id.random)
        buttonSelect = findViewById(R.id.select)
        buttonFirst = findViewById(R.id.first)
        buttonLatest = findViewById(R.id.latest)
        buttonPrevious = findViewById(R.id.previous)
        buttonNext = findViewById(R.id.next)
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
            R.id.latest -> controller.last()
            R.id.previous -> controller.previous()
            R.id.next -> controller.next()
            R.id.select -> {
                val selectComicDialogFragment = SelectComicDialogFragment(controller)
                selectComicDialogFragment.show(supportFragmentManager, "SELECT_COMIC_DIALOG")
            }
        }
    }

    override fun render(uiState: UIState) {
        val comic = uiState.comic ?: return
        comicTitle?.text = comic.title
        Glide.with(this).load(comic.img).into(comicImage!!)
        // comicImage?.tooltipText = comic.alt
        comicText?.text = comic.transcript
        comicUrlTextView?.text = comic.link
        imageUrlTextView?.text = comic.img
    }

    override fun render(t: Throwable) {}
}