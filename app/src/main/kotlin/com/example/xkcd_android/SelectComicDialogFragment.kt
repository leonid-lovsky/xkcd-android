package com.example.xkcd_android

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class SelectComicDialogFragment(private val presenter: ComicPresenter) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = activity ?: throw IllegalStateException("Activity cannot be null")
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.select_comic_dialog, null) as View
        val inputView = view.findViewById<EditText>(R.id.select_comic_dialog_input)
        val builder = AlertDialog.Builder(activity)
            .setView(view)
            .setPositiveButton(R.string.select_comic_dialog_positive_button) { _, _ ->
                val comicNumber = inputView.text.toString()
                presenter.loadComicByNumber(comicNumber.toInt())
            }.setNegativeButton(R.string.select_comic_dialog_negative_button) { _, _ ->
                dialog?.cancel() // TODO
            }
        return builder.create()
    }
}