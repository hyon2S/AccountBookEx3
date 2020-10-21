package com.example.accountbookex3.util

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.accountbookex3.AccountBookApplication
import com.example.accountbookex3.R
import com.example.accountbookex3.exception.StringFormException
import java.time.LocalDate
import java.time.format.DateTimeParseException

/*
* https://developer.android.com/guide/topics/ui/controls/pickers 여기 참고해서 만듦
* */
class DatePickerFragment(val dateStr: String, val tvDate: TextView): DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = try {
            LocalDate.parse(dateStr)
        } catch (e: DateTimeParseException) {
            throw StringFormException(
                AccountBookApplication.applicationContext().resources.getString(
                    R.string.date))
        }
        return DatePickerDialog(context!!, this, date.year, date.monthValue - 1, date.dayOfMonth )
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        tvDate.text = LocalDate.of(year, month + 1, dayOfMonth).toString()
    }
}
