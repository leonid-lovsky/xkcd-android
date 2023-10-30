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
import com.example.xkcd_android.contract.ComicView
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

    private lateinit var comicProgressBar: ProgressBar

    private val comicPresenter = (application as ComicApplication).comicController()

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
        comicProgressBar = findViewById(R.id.comic_progress_bar)
        comicButtonFirst.setOnClickListener(this)
        comicButtonLast.setOnClickListener(this)
        comicButtonPrevious.setOnClickListener(this)
        comicButtonNext.setOnClickListener(this)
        comicPresenter.create()
    }

    override fun onStart() {
        super.onStart()
        comicPresenter.start(this)
    }

    override fun onStop() {
        super.onStop()
        comicPresenter.stop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        comicPresenter.saveInstanceState()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        comicPresenter.restoreInstanceState()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.comic_menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.comic_menu_current -> {
                comicPresenter.latest()
                true
            }
            R.id.comic_menu_select -> {
                comicPresenter.select()
                true
            }
            R.id.comic_menu_refresh -> {
                comicPresenter.refresh()
                true
            }
            R.id.comic_menu_random -> {
                comicPresenter.random()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.comic_button_first -> comicPresenter.first()
            R.id.comic_button_last -> comicPresenter.last()
            R.id.comic_button_previous -> comicPresenter.previous()
            R.id.comic_button_next -> comicPresenter.next()
        }
    }

    override fun render(comicState: ComicState) {
        val comic = comicState.comic ?: return
        Picasso.get().load(comic.img).into(comicImageView)
        comicTitleView.text = comic.title
        comicLinkTextView.text = comic.link
        comicImageUrlTextView.text = comic.img
    }

    override fun render(error: Throwable) {
    }

    override fun showComicSelectDialog() {
        val comicSelectDialogFragment = ComicSelectDialogFragment(comicPresenter)
        comicSelectDialogFragment.show(supportFragmentManager, "COMIC_SELECT_DIALOG_FRAGMENT")
    }

    override fun showProgress() {
        comicProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        comicProgressBar.visibility = View.INVISIBLE
    }
}