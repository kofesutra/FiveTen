package ru.kofesutra.fiveten.utils

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ru.kofesutra.fiveten.R

class YouWin : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Вы выиграли!")
                .setMessage("Крутенько, да? ;)")
//                .setIcon(R.drawable.ic_baseline_all_out_24)
                .setPositiveButton("Зашибись!") {
                        dialog, id ->  dialog.cancel()
                }
//                .setNegativeButton("Непонятно") {
//                        dialog, id ->  Toast.makeText(activity, "Штош, попробуйте ещё раз", Toast.LENGTH_SHORT).show()
//                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
} //