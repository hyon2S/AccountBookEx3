package com.example.accountbookex3.util

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
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
class DatePickerFragment(): DialogFragment(), DatePickerDialog.OnDateSetListener {
    private val TAG = "DatePickerFragmentLog"

    var attachedTextView: TextView? = null

    /*
    * show() 직후, 화면회전 직후에 호출됨.
    * */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG, "onCreateDialog()")
        val date = updateDate()
        return DatePickerDialog(context!!, this, date.year, date.monthValue - 1, date.dayOfMonth)
    }

    private fun updateDate(): LocalDate {
        Log.d(TAG, "updateDate()")
        val date = try {
            LocalDate.parse(attachedTextView?.text)
        } catch (e: DateTimeParseException) {
            throw StringFormException(
                    AccountBookApplication.applicationContext().resources.getString(
                            R.string.date))
        }
        Log.d(TAG, "date: ${date}")
        return date
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Log.d(TAG, "onDateSet()")
        attachedTextView?.text = LocalDate.of(year, month + 1, dayOfMonth).toString()
    }
}
