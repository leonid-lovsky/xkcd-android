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
import com.squareup.picasso.Picasso

class ComicActivity : AppCompatActivity(), ComicView, View.OnClickListener {

    private lateinit var toolbar: Toolbar

    private lateinit var comicTitleView: TextView
    private lateinit var comicImageView: ImageView
    private lateinit var comicDescriptionTextView: TextView
    private lateinit var comicLinkTextView: TextView
    private lateinit var comicImageUrlTextView: TextView

    private lateinit var firstComicButton: Button
    private lateinit var lastComicButton: Button
    private lateinit var previousComicButton: Button
    private lateinit var nextComicButton: Button

    private lateinit var comicProgressBar: ProgressBar

    private lateinit var presenter: ComicViewController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_activity)

        toolbar = findViewById(R.id.comic_toolbar)
        setSupportActionBar(toolbar)

        comicTitleView = findViewById(R.id.comic_title)
        comicImageView = findViewById(R.id.comic_image)
        comicDescriptionTextView = findViewById(R.id.comic_description)
        comicLinkTextView = findViewById(R.id.comic_link)
        comicImageUrlTextView = findViewById(R.id.comic_image_url)

        firstComicButton = findViewById(R.id.first_comic_button)
        lastComicButton = findViewById(R.id.last_comic_button)
        previousComicButton = findViewById(R.id.previous_comic_button)
        nextComicButton = findViewById(R.id.next_comic_button)

        comicProgressBar = findViewById(R.id.comic_progress_bar)

        firstComicButton.setOnClickListener(this)
        lastComicButton.setOnClickListener(this)
        previousComicButton.setOnClickListener(this)
        nextComicButton.setOnClickListener(this)

        presenter = (application as ComicApplication).comicPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.setView(this)
        presenter.restoreState()
    }

    override fun onStop() {
        super.onStop()
        presenter.setView(null)
        presenter.saveState()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.comic_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.latest_comic -> {
                presenter.loadLatestComic()
                true
            }
            R.id.select_comic -> {
                presenter.displaySelectComicDialog()
                true
            }
            R.id.refresh_comic -> {
                presenter.refreshCurrentComic()
                true
            }
            R.id.random_comic -> {
                presenter.loadRandomComic()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.first_comic_button -> presenter.loadFirstComic()
            R.id.last_comic_button -> presenter.loadLastComic()
            R.id.previous_comic_button -> presenter.loadPreviousComic()
            R.id.next_comic_button -> presenter.loadNextComic()
        }
    }

    override fun render(comicData: ComicData) {
        comicTitleView.text = comicData.title
        Picasso.get().load(comicData.img).into(comicImageView)
        // comic.
        comicLinkTextView.text = comicData.link
        comicImageUrlTextView.text = comicData.img
    }

    override fun render(error: Throwable) {
        print(error) // TODO
    }

    override fun render(message: String) {
        print(message) // TODO
    }

    override fun displaySelectComicDialog() {
        val selectComicDialogFragment = SelectComicDialogFragment(presenter)
        selectComicDialogFragment.show(supportFragmentManager, "SELECT_COMIC_DIALOG_FRAGMENT")
    }

    override fun showProgress() {
        comicProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        comicProgressBar.visibility = View.INVISIBLE
    }
}