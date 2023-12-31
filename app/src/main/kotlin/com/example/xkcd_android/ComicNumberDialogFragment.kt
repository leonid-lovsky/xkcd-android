package com.example.xkcd_android

import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import timber.log.Timber

class ComicNumberDialogFragment(
    private val viewModel: ComicViewModel,
) : DialogFragment(), OnClickListener {

    private val comicNumberInput: TextView by lazy { requireDialog().findViewById(R.id.comic_number_input) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = activity ?: throw IllegalStateException("Activity cannot be null")
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.comic_number_dialog, null)
        val builder = AlertDialog.Builder(activity).setView(view)
            .setTitle(R.string.select_comic_dialog_title)
            // .setMessage(R.string.select_comic_dialog_message)
            .setPositiveButton(R.string.select_comic_dialog_positive_button, this)
            .setNegativeButton(R.string.select_comic_dialog_negative_button, this)
        return builder.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            BUTTON_POSITIVE -> {
                try {
                    val comicNumber = comicNumberInput.text.toString().toInt()
                    viewModel.toComic(comicNumber)
                } catch (e: Throwable) {
                    Timber.e(e)
                }
            }
            BUTTON_NEGATIVE -> {
                dialog?.cancel()
            }
        }
    }
}
