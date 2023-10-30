package com.example.xkcd_android

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ComicSelectDialogFragment(private val comicPresenter: ComicPresenterDefault) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = activity ?: throw IllegalStateException("Activity cannot be null")
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.comic_select_dialog, null) as View
        val inputView = view.findViewById<EditText>(R.id.comic_select_dialog_input)
        val builder = AlertDialog.Builder(activity)
            .setView(view)
            .setPositiveButton(R.string.comic_select_dialog_positive_button) { _, _ ->
                val comicNumber = inputView.text.toString()
                comicPresenter.select(comicNumber.toInt())
            }.setNegativeButton(R.string.comic_select_dialog_negative_button) { _, _ ->
                dialog?.cancel()
            }
        return builder.create()
    }
}