package com.example.accountbookex3

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDate

object BindingConversions {
    @BindingAdapter("android:text")
    @JvmStatic
    fun bindDate(textView: TextView, date: LocalDate) {
        textView.text = date.toString()
    }
}