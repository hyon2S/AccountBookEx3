package com.example.accountbookex3.util

import android.util.Log
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

class TextViewDatePickerCreator
    (private val activity: FragmentActivity, private val tag: String, var tvDate: TextView? = null) {
    private val TAG = "TextViewDatePickerCreatorLog"

    fun showDatePickerDialog() {
        Log.d(TAG, "showDatePickerDialog()")
        try {
            DatePickerFragment(
                tvDate!!.text.toString(),
                tvDate!!
            )
                .show(activity.supportFragmentManager, tag)
        } catch (e: NullPointerException) {
            Log.d(TAG, "TextView를 지정해주세요.")
        }
    }
}
