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

    private val comicToolbar: Toolbar by lazy { findViewById(R.id.comic_toolbar) }

    private val comicTitleView: TextView by lazy { findViewById(R.id.comic_title_view) }
    private val comicImageView: ImageView by lazy { findViewById(R.id.comic_image_view) }
    private val comicUrlView: TextView by lazy { findViewById(R.id.comic_url_view) }
    private val comicImageUrlView: TextView by lazy { findViewById(R.id.comic_image_url_view) }

    private val comicProgressBar: ProgressBar by lazy { findViewById(R.id.comic_progress_bar) }

    private val firstComicButton: Button by lazy { findViewById(R.id.first_comic_button) }
    private val lastComicButton: Button by lazy { findViewById(R.id.last_comic_button) }
    private val previousComicButton: Button by lazy { findViewById(R.id.previous_comic_button) }
    private val nextComicButton: Button by lazy { findViewById(R.id.next_comic_button) }

    private val swipeRefreshLayout: SwipeRefreshLayout by lazy { findViewById(R.id.comic_refresh_layout) }

    private val comicViewModel: ComicViewModel by viewModels()

    // private val preferences: SharedPreferences by lazy { getSharedPreferences("preferences", Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_activity)
        setSupportActionBar(comicToolbar)

        firstComicButton.setOnClickListener(this)
        lastComicButton.setOnClickListener(this)
        previousComicButton.setOnClickListener(this)
        nextComicButton.setOnClickListener(this)

        swipeRefreshLayout.setOnRefreshListener(this)

        // if (savedInstanceState == null) {
        //     val currentComicNumber = preferences.getInt("current_comic_number", -1)
        //     if (currentComicNumber == -1) {
        //         comicViewModel.getLatestComic()
        //     } else {
        //         comicViewModel.getComic(currentComicNumber)
        //     }
        // }

        comicViewModel.loading.observe(this) { value ->
            Timber.i(value.toString())
            comicProgressBar.visibility = if (value) View.VISIBLE else View.VISIBLE
            swipeRefreshLayout.isRefreshing = value
        }

        comicViewModel.comic.observe(this) { value ->
            Timber.i(value.toString())
            if (value != null) {
                comicTitleView.text = value.title
                Picasso.get().load(value.img).into(comicImageView)
                comicUrlView.text = value.url
                comicImageUrlView.text = value.img
                // preferences.edit().putInt("current_comic_number", value.num).apply()
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

    override fun onRefresh() {
        comicViewModel.refreshComic()
    }
}
