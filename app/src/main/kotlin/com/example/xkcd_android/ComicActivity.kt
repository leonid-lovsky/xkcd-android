package com.example.xkcd_android

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ComicActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var comicToolbar: Toolbar

    private lateinit var comicTitleView: TextView
    private lateinit var comicImageView: ImageView
    private lateinit var comicDescriptionView: TextView
    private lateinit var comicUrlView: TextView
    private lateinit var comicImageUrlView: TextView

    private lateinit var comicProgressBar: ProgressBar

    private lateinit var firstComicButton: Button
    private lateinit var previousComicButton: Button
    private lateinit var nextComicButton: Button
    private lateinit var lastComicButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_activity)

        comicToolbar = findViewById(R.id.comic_toolbar)
        setSupportActionBar(comicToolbar)

        comicTitleView = findViewById(R.id.comic_title)
        comicImageView = findViewById(R.id.comic_image)
        comicDescriptionView = findViewById(R.id.comic_description)
        comicUrlView = findViewById(R.id.comic_url)
        comicImageUrlView = findViewById(R.id.comic_image_url)

        comicProgressBar = findViewById(R.id.comic_progress_bar)

        firstComicButton = findViewById(R.id.first_comic_button)
        previousComicButton = findViewById(R.id.previous_comic_button)
        nextComicButton = findViewById(R.id.next_comic_button)
        lastComicButton = findViewById(R.id.last_comic_button)

        firstComicButton.setOnClickListener(this)
        previousComicButton.setOnClickListener(this)
        nextComicButton.setOnClickListener(this)
        lastComicButton.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.comic_main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.latest_comic -> {
                true
            }

            R.id.select_comic -> {
                true
            }

            R.id.refresh_comic -> {
                true
            }

            R.id.random_comic -> {
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.first_comic_button -> {}
            R.id.previous_comic_button -> {}
            R.id.next_comic_button -> {}
            R.id.last_comic_button -> {}
        }
    }

    companion object {
        const val SELECT_COMIC_DIALOG_FRAGMENT_TAG = "SELECT_COMIC_DIALOG_FRAGMENT"
    }
}