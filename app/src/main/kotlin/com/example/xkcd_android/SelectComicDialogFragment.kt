package com.example.xkcd_android

import android.app.Dialog
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class SelectComicDialogFragment(private val controller: Controller) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = activity ?: throw IllegalStateException("Activity cannot be null")
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_select_comic, null) as View
        val editText = view.findViewById<EditText>(R.id.edit_text)
        val builder = AlertDialog.Builder(activity)
            .setView(view)
            .setPositiveButton("Select") { _, _ ->
                val number = editText.text.toString()
                controller.select(number.toInt())
            }
            .setNegativeButton("Cancel") { _, _ ->
                dialog?.cancel()
            }
        return builder.create()
    }
}