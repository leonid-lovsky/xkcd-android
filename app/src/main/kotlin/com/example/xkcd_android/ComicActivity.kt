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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ComicActivity : AppCompatActivity(), ComicScreen, View.OnClickListener {

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
    private val comicViewModel: ComicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_activity)
        setSupportActionBar(comicToolbar)
        firstComicButton.setOnClickListener(this)
        lastComicButton.setOnClickListener(this)
        previousComicButton.setOnClickListener(this)
        nextComicButton.setOnClickListener(this)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {}
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.comic_main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.latest_comic -> {
                comicViewModel.loadLatestComic()
            }
            R.id.select_comic -> {
                displaySelectComicDialog()
            }
            R.id.refresh_comic -> {
                comicViewModel.refreshComic()
            }
            R.id.random_comic -> {
                comicViewModel.loadRandomComic()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.first_comic_button -> {
                comicViewModel.loadFirstComic()
            }
            R.id.last_comic_button -> {
                comicViewModel.loadLastComic()
            }
            R.id.previous_comic_button -> {
                comicViewModel.loadPreviousComic()
            }
            R.id.next_comic_button -> {
                comicViewModel.loadNextComic()
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
        val comicDialogFragment = ComicDialogFragment(comicViewModel)
        comicDialogFragment.show(supportFragmentManager, ComicDialogFragment::class.qualifiedName)
    }

    override fun showProgressBar() {
        comicProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        comicProgressBar.visibility = View.INVISIBLE
    }

    override fun handleException(exception: Throwable) {
        Log.e(this::class.qualifiedName, exception.message, exception.cause)
    }
}
