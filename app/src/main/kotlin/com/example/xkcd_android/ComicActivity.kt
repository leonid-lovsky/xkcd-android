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
class ComicActivity : AppCompatActivity(), View.OnClickListener {

    private val comicToolbar: Toolbar by lazy { findViewById(R.id.comic_toolbar) }
    private val comicTitleView: TextView by lazy { findViewById(R.id.comic_title_view) }
    private val comicImageView: ImageView by lazy { findViewById(R.id.comic_image_view) }
    private val comicLinkView: TextView by lazy { findViewById(R.id.comic_link_view) }
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
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                comicViewModel.currentComic.collect { comic ->
                    if (comic == null) return@collect
                    comicToolbar.title = resources.getString(R.string.comic_activity_title, comic.num)
                    comicTitleView.text = comic.title
                    Picasso.get().load(comic.img).into(comicImageView)
                    comicLinkView.text = comic.link
                    comicImageUrlView.text = comic.img
                }
            }
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                comicViewModel.isLoading.collect { value ->
                    comicProgressBar.visibility = if (value) View.VISIBLE else View.GONE
                }
            }
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
                comicViewModel.getLatestComic()
            }
            R.id.select_comic -> {
                val comicNumberDialogFragment = ComicNumberDialogFragment(comicViewModel)
                comicNumberDialogFragment.show(supportFragmentManager, ComicNumberDialogFragment::class.qualifiedName)
            }
            R.id.refresh_comic -> {
                comicViewModel.refreshComic()
            }
            R.id.random_comic -> {
                comicViewModel.getRandomComic()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.first_comic_button -> {
                comicViewModel.getFirstComic()
            }
            R.id.last_comic_button -> {
                comicViewModel.getLastComic()
            }
            R.id.previous_comic_button -> {
                comicViewModel.getPreviousComic()
            }
            R.id.next_comic_button -> {
                comicViewModel.getNextComic()
            }
        }
    }
}
