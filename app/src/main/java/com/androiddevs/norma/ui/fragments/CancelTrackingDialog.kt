package com.androiddevs.norma.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.androiddevs.norma.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CancelTrackingDialog : DialogFragment() {

    private var yesListener: (() -> Unit)? = null

    fun setYesListener(listener: () -> Unit) {
        yesListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle(getString(R.string.cancel_the_run))
            .setMessage(getString(R.string.cancel_the_run_warning))
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                yesListener?.let { yes ->
                    yes()
                }
            }
            .setNegativeButton(getString(R.string.no)) { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
    }
}