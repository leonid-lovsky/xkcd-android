package com.example.xkcd_android

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class SelectComicDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = activity ?: throw IllegalStateException("Activity cannot be null")
        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        builder.setView(inflater.inflate(R.layout.dialog_select_comic, null))
        builder.setPositiveButton("Select") { _, _ ->
            // Sign in the user.
        }
        builder.setNegativeButton("Cancel") { _, _ ->
            dialog?.cancel()
        }
        return builder.create()
    }
}