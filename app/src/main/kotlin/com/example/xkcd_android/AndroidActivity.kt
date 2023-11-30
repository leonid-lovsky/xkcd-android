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

class AndroidActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var toolbar: Toolbar

    private lateinit var comicTitleView: TextView
    private lateinit var comicImageView: ImageView
    private lateinit var comicTextView: TextView
    private lateinit var comicLinkView: TextView
    private lateinit var comicImageUrlView: TextView

    private lateinit var firstComicButton: Button
    private lateinit var lastComicButton: Button
    private lateinit var previousComicButton: Button
    private lateinit var nextComicButton: Button

    private lateinit var comicProgressBar: ProgressBar

    private lateinit var comicRepository: ComicRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_activity)

        toolbar = findViewById(R.id.comic_toolbar)
        setSupportActionBar(toolbar)

        comicTitleView = findViewById(R.id.comic_title)
        comicImageView = findViewById(R.id.comic_image)
        comicTextView = findViewById(R.id.comic_description)
        comicLinkView = findViewById(R.id.comic_link)
        comicImageUrlView = findViewById(R.id.comic_image_url)

        firstComicButton = findViewById(R.id.first_comic_button)
        lastComicButton = findViewById(R.id.last_comic_button)
        previousComicButton = findViewById(R.id.previous_comic_button)
        nextComicButton = findViewById(R.id.next_comic_button)

        comicProgressBar = findViewById(R.id.comic_progress_bar)

        firstComicButton.setOnClickListener(this)
        lastComicButton.setOnClickListener(this)
        previousComicButton.setOnClickListener(this)
        nextComicButton.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.comic_main_menu, menu)
        return true
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
            R.id.first_comic_button -> {
            }
            R.id.last_comic_button -> {
            }
            R.id.previous_comic_button -> {
            }
            R.id.next_comic_button -> {
            }
        }
    }

    //    override fun render(comic: com.example.data.Comic) {
    //        comicTitleView.text = comic.title
    //        Picasso.get().load(comic.img).into(comicImageView)
    //        comicLinkTextView.text = comic.link
    //        comicImageUrlTextView.text = comic.img
    //    }
    //
    //    override fun render(error: Throwable) {
    //        print(error)
    //    }
    //
    //    override fun render(message: String) {
    //        print(message)
    //    }
    //
    //    override fun displaySelectComicDialog() {
    //        val selectComicDialogFragment = SelectComicDialogFragment(presenter)
    //        selectComicDialogFragment.show(supportFragmentManager, SELECT_COMIC_DIALOG_FRAGMENT_TAG)
    //    }
    //
    //    override fun showProgress() {
    //        comicProgressBar.visibility = View.VISIBLE
    //    }
    //
    //    override fun hideProgress() {
    //        comicProgressBar.visibility = View.INVISIBLE
    //    }

    companion object {
        const val SELECT_COMIC_DIALOG_FRAGMENT_TAG = "SELECT_COMIC_DIALOG_FRAGMENT"
    }
}