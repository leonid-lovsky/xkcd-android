package com.example.xkcd_android

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso

class ComicActivity : AppCompatActivity(), ComicView, View.OnClickListener {
    private lateinit var toolbar: Toolbar

    private lateinit var comicImageView: ImageView
    private lateinit var comicTitleView: TextView
    private lateinit var comicDescriptionTextView: TextView
    private lateinit var comicLinkTextView: TextView
    private lateinit var comicImageUrlTextView: TextView

    private lateinit var comicButtonFirst: Button
    private lateinit var comicButtonLast: Button
    private lateinit var comicButtonPrevious: Button
    private lateinit var comicButtonNext: Button

    private val comicController = (application as ComicApplication).comicController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        comicTitleView = findViewById(R.id.comic_title)
        comicImageView = findViewById(R.id.comic_image)
        comicDescriptionTextView = findViewById(R.id.comic_description)
        comicLinkTextView = findViewById(R.id.comic_link)
        comicImageUrlTextView = findViewById(R.id.comic_image_url)
        comicButtonFirst = findViewById(R.id.comic_button_first)
        comicButtonLast = findViewById(R.id.comic_button_last)
        comicButtonPrevious = findViewById(R.id.comic_button_previous)
        comicButtonNext = findViewById(R.id.comic_button_next)
        comicButtonFirst.setOnClickListener(this)
        comicButtonLast.setOnClickListener(this)
        comicButtonPrevious.setOnClickListener(this)
        comicButtonNext.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        comicController.comicView = this
    }

    override fun onStop() {
        super.onStop()
        comicController.comicView = null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.comic_menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.comic_menu_current -> {
                comicController.current()
                true
            }
            R.id.comic_menu_random -> {
                comicController.random()
                true
            }
            R.id.comic_menu_select -> {
                val comicSelectDialogFragment = ComicSelectDialogFragment(comicController)
                comicSelectDialogFragment.show(supportFragmentManager, "COMIC_SELECT_DIALOG_FRAGMENT")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.comic_button_first -> comicController.first()
            R.id.comic_button_last -> comicController.last()
            R.id.comic_button_previous -> comicController.previous()
            R.id.comic_button_next -> comicController.next()
        }
    }

    override fun render(comicUIState: ComicUIState) {
        val comic = comicUIState.comic
        Picasso.get().load(comic.img).into(comicImageView)
        comicTitleView.text = comic.title
        comicLinkTextView.text = comic.link
        comicImageUrlTextView.text = comic.img
    }

    override fun render(t: Throwable) {}
}