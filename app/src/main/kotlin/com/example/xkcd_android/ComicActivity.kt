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
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso

class ComicActivity : AppCompatActivity(), View.OnClickListener {

    private val comicToolbar: Toolbar by lazy { findViewById(R.id.comic_toolbar) }

    private val comicTitleView: TextView by lazy { findViewById(R.id.comic_title_view) }
    private val comicImageView: ImageView by lazy { findViewById(R.id.comic_image_view) }
    private val comicDescriptionView: TextView by lazy { findViewById(R.id.comic_description_view) }
    private val comicUrlView: TextView by lazy { findViewById(R.id.comic_url_view) }
    private val comicImageUrlView: TextView by lazy { findViewById(R.id.comic_image_url_view) }

    private val comicProgressBar: ProgressBar by lazy { findViewById(R.id.comic_progress_bar) }

    private val firstComicButton: Button by lazy { findViewById(R.id.first_comic_button) }
    private val lastComicButton: Button by lazy { findViewById(R.id.last_comic_button) }
    private val previousComicButton: Button by lazy { findViewById(R.id.previous_comic_button) }
    private val nextComicButton: Button by lazy { findViewById(R.id.next_comic_button) }

    private val comicViewModel: ComicViewModel by viewModels { ComicViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_activity)
        setSupportActionBar(comicToolbar)
        firstComicButton.setOnClickListener(this)
        lastComicButton.setOnClickListener(this)
        previousComicButton.setOnClickListener(this)
        nextComicButton.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.comic_main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.latest_comic -> {
                comicViewModel.latestComic()
            }
            R.id.select_comic -> {
                displaySelectComicDialog()
            }
            R.id.refresh_comic -> {
                comicViewModel.refreshComic()
            }
            R.id.random_comic -> {
                comicViewModel.randomComic()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.first_comic_button -> {
                comicViewModel.firstComic()
            }
            R.id.last_comic_button -> {
                comicViewModel.lastComic()
            }
            R.id.previous_comic_button -> {
                comicViewModel.previousComic()
            }
            R.id.next_comic_button -> {
                comicViewModel.nextComic()
            }
        }
    }

    fun displayComic(comic: Comic) {
        comicTitleView.text = comic.title
        Picasso.get().load(comic.img).into(comicImageView)
        comicUrlView.text = comic.link
        comicImageUrlView.text = comic.img
    }

    fun displaySelectComicDialog() {
        val selectComicDialogFragment = SelectComicDialogFragment(comicViewModel)
        selectComicDialogFragment.show(supportFragmentManager, SELECT_COMIC_DIALOG_FRAGMENT_TAG)
    }

    fun showProgressBar() {
        comicProgressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        comicProgressBar.visibility = View.INVISIBLE
    }

    fun handleError(e: Throwable) {
        Log.e(this::class.simpleName, e.message, e.cause)
    }

    companion object {

        const val SELECT_COMIC_DIALOG_FRAGMENT_TAG = "SELECT_COMIC_DIALOG_FRAGMENT"
    }
}
