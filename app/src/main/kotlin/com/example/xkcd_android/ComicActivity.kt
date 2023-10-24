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

class ComicActivity : AppCompatActivity(), ComicPresenter, View.OnClickListener {
    private lateinit var toolbar: Toolbar

    private lateinit var comicImageView: ImageView
    private lateinit var comicTitleView: TextView
    private lateinit var comicDescriptionTextView: TextView
    private lateinit var comicUrlTextView: TextView
    private lateinit var comicImageUrlTextView: TextView

    private lateinit var buttonComicFirst: Button
    private lateinit var buttonComicLast: Button
    private lateinit var buttonComicPrevious: Button
    private lateinit var buttonComicNext: Button

    private val comicController = (application as ComicDependencies).comicController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        comicTitleView = findViewById(R.id.comic_title)
        comicImageView = findViewById(R.id.comic_image)
        comicDescriptionTextView = findViewById(R.id.comic_description)
        comicUrlTextView = findViewById(R.id.comic_link)
        comicImageUrlTextView = findViewById(R.id.comic_image_url)
        buttonComicFirst = findViewById(R.id.button_comic_first)
        buttonComicLast = findViewById(R.id.button_comic_last)
        buttonComicPrevious = findViewById(R.id.button_comic_previous)
        buttonComicNext = findViewById(R.id.button_comic_next)
        buttonComicFirst.setOnClickListener(this)
        buttonComicLast.setOnClickListener(this)
        buttonComicPrevious.setOnClickListener(this)
        buttonComicNext.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        comicController.comicPresenter = this
    }

    override fun onStop() {
        super.onStop()
        comicController.comicPresenter = null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.comic_menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_comic_current -> {
                comicController.current()
                true
            }
            R.id.menu_comic_random -> {
                comicController.random()
                true
            }
            R.id.menu_comic_select -> {
                comicController.select()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.button_comic_first -> comicController.first()
            R.id.button_comic_last -> comicController.last()
            R.id.button_comic_previous -> comicController.previous()
            R.id.button_comic_next -> comicController.next()
        }
    }

    override fun render(comicState: ComicState) {
        val comic = comicState.comic
        Picasso.get().load(comic.img).into(comicImageView)
        comicTitleView.text = comic.title
        comicUrlTextView.text = comic.link
        comicImageUrlTextView.text = comic.img
    }

    override fun render(t: Throwable) {}

    override fun showSelectComicDialog() {
        val comicSelectDialogFragment = ComicSelectDialogFragment(comicController)
        comicSelectDialogFragment.show(supportFragmentManager, "COMIC_SELECT_DIALOG_FRAGMENT")
    }
}