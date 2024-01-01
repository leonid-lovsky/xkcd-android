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

    private val toFirstComicButton: Button by lazy { findViewById(R.id.to_first_comic) }
    private val toPreviousComicButton: Button by lazy { findViewById(R.id.to_previous_comic) }
    private val toNextButton: Button by lazy { findViewById(R.id.to_next_comic) }
    private val toLastComicButton: Button by lazy { findViewById(R.id.to_last_comic) }

    private val swipeRefreshLayout: SwipeRefreshLayout by lazy { findViewById(R.id.swipe_refresh_layout) }

    private val viewModel: ComicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comic_activity)
        setSupportActionBar(toolbar)

        toFirstComicButton.setOnClickListener(this)
        toPreviousComicButton.setOnClickListener(this)
        toNextButton.setOnClickListener(this)
        toLastComicButton.setOnClickListener(this)

        swipeRefreshLayout.setOnRefreshListener(this)

        viewModel.loading.observe(this) { value ->
            Timber.i(value.toString())
            progressBar.visibility = if (value) View.VISIBLE else View.VISIBLE
            swipeRefreshLayout.isRefreshing = value
        }

        viewModel.currentComic.observe(this) { value ->
            Timber.i(value.toString())
            if (value != null) {
                comicTitleView.text = value.title
                Picasso.get().load(value.img).into(comicImageView)
                comicUrlView.text = value.url
                comicImageUrlView.text = value.img
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.comic_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.to_comic -> {
                val comicNumberDialogFragment = ComicNumberDialogFragment(viewModel)
                comicNumberDialogFragment.show(supportFragmentManager, ComicNumberDialogFragment::class.qualifiedName)
            }
            R.id.refresh_current_comic -> {
                viewModel.refreshComic()
            }
            R.id.to_random_comic -> {
                viewModel.toRandomComic()
            }
            R.id.to_latest_comic -> {
                viewModel.toLatestComic()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.to_first_comic -> {
                viewModel.toFirstComic()
            }
            R.id.to_previous_comic -> {
                viewModel.toPreviousComic()
            }
            R.id.to_next_comic -> {
                viewModel.toNextComic()
            }
            R.id.to_last_comic -> {
                viewModel.toLastComic()
            }
        }
    }

    override fun onRefresh() {
        viewModel.refreshComic()
    }
}
