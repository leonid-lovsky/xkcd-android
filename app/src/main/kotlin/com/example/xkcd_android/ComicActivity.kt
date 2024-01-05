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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ComicActivity : AppCompatActivity(), View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private val toolbar: Toolbar by lazy { findViewById(R.id.toolbar) }

    private val comicTitleView: TextView by lazy { findViewById(R.id.comic_title_view) }
    private val comicImageView: ImageView by lazy { findViewById(R.id.comic_image_view) }
    private val comicUrlView: TextView by lazy { findViewById(R.id.comic_url_view) }
    private val comicImageUrlView: TextView by lazy { findViewById(R.id.comic_image_url_view) }

    private val progressBar: ProgressBar by lazy { findViewById(R.id.progress_bar) }

    private val firstComicButton: Button by lazy { findViewById(R.id.navigate_to_first_comic) }
    private val previousComicButton: Button by lazy { findViewById(R.id.navigate_to_previous_comic) }
    private val nextButton: Button by lazy { findViewById(R.id.navigate_to_next_comic) }
    private val lastComicButton: Button by lazy { findViewById(R.id.navigate_to_last_comic) }

    private val swipeRefreshLayout: SwipeRefreshLayout by lazy { findViewById(R.id.swipe_refresh_layout) }

    private val comicViewModel: ComicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_activity)
        setSupportActionBar(toolbar)

        firstComicButton.setOnClickListener(this)
        previousComicButton.setOnClickListener(this)
        nextButton.setOnClickListener(this)
        lastComicButton.setOnClickListener(this)

        swipeRefreshLayout.setOnRefreshListener(this)

        comicViewModel.isRefreshing.observe(this) { isRefreshing ->
            Timber.i("Is refreshing: ${isRefreshing}")
            progressBar.visibility = if (isRefreshing) View.VISIBLE else View.VISIBLE
            swipeRefreshLayout.isRefreshing = isRefreshing
        }

        comicViewModel.comic.observe(this) { comic ->
            Timber.i("Comic: ${comic}")
            if (comic != null) {
                comicTitleView.text = comic.title
                Picasso.get().load(comic.img).into(comicImageView)
                comicUrlView.text = comic.url
                comicImageUrlView.text = comic.img
            }
        }

        comicViewModel.message.observe(this) { message ->
            Timber.i("Message: ${message}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.comic_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigate_to_comic_by_number -> {
                val comicNumberDialogFragment = ComicNumberDialogFragment(comicViewModel)
                comicNumberDialogFragment.show(supportFragmentManager, ComicNumberDialogFragment::class.qualifiedName)
            }
            R.id.reload_current_comic -> {
                comicViewModel.navigateToCurrentComic()
            }
            R.id.navigate_to_random_comic -> {
                comicViewModel.navigateToRandomComic()
            }
            R.id.navigate_to_latest_comic -> {
                comicViewModel.navigateToLatestComic()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.navigate_to_first_comic -> {
                comicViewModel.navigateToFirstComic()
            }
            R.id.navigate_to_previous_comic -> {
                comicViewModel.navigateToPreviousComic()
            }
            R.id.navigate_to_next_comic -> {
                comicViewModel.navigateToNextComic()
            }
            R.id.navigate_to_last_comic -> {
                comicViewModel.navigateToLastComic()
            }
        }
    }

    override fun onRefresh() {
        comicViewModel.navigateToCurrentComic()
    }
}
