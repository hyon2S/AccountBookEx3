package com.example.accountbookex3

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat
import java.time.LocalDate

object BindingConversions {
    const val TAG = "BindingConversionsLog"

    @BindingAdapter("android:text")
    @JvmStatic
    fun bindDate(textView: TextView, date: LocalDate) {
        // Log.d(TAG, "bindDate()")
        textView.text = date.toString()
    }

    val decimalFormatter = DecimalFormat("###,###")

    @BindingAdapter("android:text")
    @JvmStatic
    fun bindAmount(textView: TextView, amount: Int) {
        // Log.d(TAG, "bindAmount()")
        textView.text = decimalFormatter.format(amount).toString()
    }

    @BindingAdapter("android:text")
    @JvmStatic
    fun bindIncomeOutcome(textView: TextView, isIncome: Boolean) {
        // Log.d(TAG, "bindIncomeOutcome()")
        textView.text = if (isIncome)
            AccountBookApplication.applicationContext().getString(R.string.income)
        else
            AccountBookApplication.applicationContext().getString(R.string.outcome)
    }
}