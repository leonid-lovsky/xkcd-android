package com.example.xkcd_android

import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class ComicActivity : AppCompatActivity(), View.OnClickListener, ComicScreen {

    private lateinit var comicToolbar: Toolbar

    private lateinit var comicTitleView: TextView
    private lateinit var comicImageView: ImageView
    private lateinit var comicDescriptionView: TextView
    private lateinit var comicUrlView: TextView
    private lateinit var comicImageUrlView: TextView

    private lateinit var comicProgressBar: ProgressBar

    private lateinit var firstComicButton: Button
    private lateinit var lastComicButton: Button
    private lateinit var previousComicButton: Button
    private lateinit var nextComicButton: Button

    private lateinit var comicController: ComicController
    private lateinit var comicViewModel: ComicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_activity)

        comicToolbar = findViewById(R.id.comic_toolbar)
        setSupportActionBar(comicToolbar)

        comicTitleView = findViewById(R.id.comic_title_view)
        comicImageView = findViewById(R.id.comic_image_view)
        comicDescriptionView = findViewById(R.id.comic_description_view)
        comicUrlView = findViewById(R.id.comic_url_view)
        comicImageUrlView = findViewById(R.id.comic_image_url_view)

        comicProgressBar = findViewById(R.id.comic_progress_bar)

        firstComicButton = findViewById(R.id.first_comic_button)
        lastComicButton = findViewById(R.id.last_comic_button)
        previousComicButton = findViewById(R.id.previous_comic_button)
        nextComicButton = findViewById(R.id.next_comic_button)

        firstComicButton.setOnClickListener(this)
        lastComicButton.setOnClickListener(this)
        previousComicButton.setOnClickListener(this)
        nextComicButton.setOnClickListener(this)

        val comicRepository = ComicRepository()
        val comicViewModelFactory = ComicViewModelFactory(comicRepository)
        val viewModelProvider = ViewModelProvider(this, comicViewModelFactory)

        comicController = viewModelProvider[ComicViewModel::class.java]
        comicViewModel = viewModelProvider[ComicViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.comic_main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.latest_comic -> {
                comicController.latestComic()
            }
            R.id.select_comic -> {
                displaySelectComicDialog()
            }
            R.id.refresh_comic -> {
                comicController.refreshComic()
            }
            R.id.random_comic -> {
                comicController.randomComic()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.first_comic_button -> {
                comicController.firstComic()
            }
            R.id.last_comic_button -> {
                comicController.lastComic()
            }
            R.id.previous_comic_button -> {
                comicController.previousComic()
            }
            R.id.next_comic_button -> {
                comicController.nextComic()
            }
        }
    }

    override fun displayComic(comic: Comic) {
        comicTitleView.text = comic.title
        Picasso.get().load(comic.img).into(comicImageView)
        comicUrlView.text = comic.link
        comicImageUrlView.text = comic.img
    }

    override fun displaySelectComicDialog() {
        val selectComicDialogFragment = SelectComicDialogFragment(comicController)
        selectComicDialogFragment.show(supportFragmentManager, SELECT_COMIC_DIALOG_FRAGMENT_TAG)
    }

    override fun showProgressBar() {
        comicProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        comicProgressBar.visibility = View.INVISIBLE
    }

    override fun handleError(e: Throwable) {
        Log.e(ComicActivity.toString(), e.message, e.cause)
    }

    companion object {

        const val SELECT_COMIC_DIALOG_FRAGMENT_TAG = "SELECT_COMIC_DIALOG_FRAGMENT"
    }
}
