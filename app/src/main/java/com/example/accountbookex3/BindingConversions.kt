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

    @BindingAdapter("android:text")
    @JvmStatic
    fun bindAmount(textView: TextView, amount: Int) {
        Log.d(TAG, "bindAmount()")
        textView.text = amount.toString()
    }
}