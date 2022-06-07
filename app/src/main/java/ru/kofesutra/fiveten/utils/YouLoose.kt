package ru.kofesutra.fiveten.utils

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class YouLoose : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Вы проиграли")
                .setMessage("Андрюша сегодня поудачливее ;)")
//                .setIcon(R.drawable.ic_baseline_all_out_24)
                .setPositiveButton("Понятненько") {
                        dialog, id ->  dialog.cancel()
                }
//                .setNegativeButton("Непонятно") {
//                        dialog, id ->  Toast.makeText(activity, "Штош, попробуйте ещё раз", Toast.LENGTH_SHORT).show()
//                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
} //