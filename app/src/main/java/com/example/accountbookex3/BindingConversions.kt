package com.example.accountbookex3

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDate

object BindingConversions {
    const val TAG = "BindingConversionsLog"

    @BindingAdapter("android:text")
    @JvmStatic
    fun bindDate(textView: TextView, date: LocalDate) {
        Log.d(TAG, "bindDate()")
        textView.text = date.toString()
    }
}