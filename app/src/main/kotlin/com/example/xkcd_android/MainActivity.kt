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

class MainActivity : AppCompatActivity(), Presenter, View.OnClickListener {
    private var toolbar: Toolbar? = null

    private var comicImageView: ImageView? = null
    private var comicTitleView: TextView? = null
    private var comicDescriptionTextView: TextView? = null
    private var comicUrlTextView: TextView? = null
    private var imageUrlTextView: TextView? = null

    private var buttonFirst: Button? = null
    private var buttonLatest: Button? = null
    private var buttonPrevious: Button? = null
    private var buttonNext: Button? = null

    private val repository = RoomInteractor()
    private val controller = Controller(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        comicTitleView = findViewById(R.id.comic_title)
        comicImageView = findViewById(R.id.comic_image)
        comicDescriptionTextView = findViewById(R.id.comic_description)
        comicUrlTextView = findViewById(R.id.comic_link)
        imageUrlTextView = findViewById(R.id.image_url)
        buttonFirst = findViewById(R.id.button_first)
        buttonLatest = findViewById(R.id.button_last)
        buttonPrevious = findViewById(R.id.button_previous)
        buttonNext = findViewById(R.id.button_next)
        buttonFirst?.setOnClickListener(this)
        buttonLatest?.setOnClickListener(this)
        buttonPrevious?.setOnClickListener(this)
        buttonNext?.setOnClickListener(this)
        controller.presenter = this
    }

    override fun onDestroy() {
        controller.presenter = null
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_current -> {
                controller.current()
                true
            }

            R.id.menu_random -> {
                controller.random()
                true
            }

            R.id.menu_select -> {
                controller.select()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.button_first -> controller.first()
            R.id.button_last -> controller.last()
            R.id.button_previous -> controller.previous()
            R.id.button_next -> controller.next()
        }
    }

    override fun render(state: State) {
        val comic = state.comic ?: return
        Picasso.get()
            .load(comic.img)
            .into(comicImageView!!)
        title = comic.title
        comicTitleView!!.text = comic.title
        comicUrlTextView!!.text = comic.link
        imageUrlTextView!!.text = comic.img
    }

    override fun render(t: Throwable) {}

    override fun showSelectComicDialog() {
        val selectComicDialogFragment = SelectComicDialogFragment(controller)
        selectComicDialogFragment.show(supportFragmentManager, "SELECT_COMIC_DIALOG")
    }
}