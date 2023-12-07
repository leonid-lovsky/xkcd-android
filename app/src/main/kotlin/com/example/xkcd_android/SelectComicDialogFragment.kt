package com.example.xkcd_android

import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class SelectComicDialogFragment(private val comicController: ComicController) : DialogFragment(), OnClickListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = activity ?: throw IllegalStateException("Activity cannot be null")
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.select_comic_dialog, null) as View
        val builder = AlertDialog.Builder(activity).setView(view)
            .setPositiveButton(R.string.select_comic_dialog_positive_button, this)
            .setNegativeButton(R.string.select_comic_dialog_negative_button, this)
        return builder.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            BUTTON_POSITIVE -> {
                val selectComicInput = activity?.findViewById<EditText>(R.id.select_comic_input)
                val comicNumber = selectComicInput?.text.toString().toIntOrNull() ?: return
                comicController.selectComic(comicNumber)
            }
            BUTTON_NEGATIVE -> {
                dialog?.cancel()
            }
        }
    }
}
