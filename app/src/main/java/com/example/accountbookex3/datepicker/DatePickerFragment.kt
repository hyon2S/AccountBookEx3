package com.example.accountbookex3.datepicker

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.AccountBookApplication
import com.example.accountbookex3.R
import com.example.accountbookex3.exception.StringFormException
import com.example.accountbookex3.viewmodel.DatePickerViewModel
import java.time.LocalDate
import java.time.format.DateTimeParseException

/*
* https://developer.android.com/guide/topics/ui/controls/pickers 여기 참고해서 만듦
* */
class DatePickerFragment(): DialogFragment(), DatePickerDialog.OnDateSetListener {
    private val TAG = "DatePickerFragmentLog"

    val datePickerViewModel by lazy { ViewModelProvider(requireActivity()).get(DatePickerViewModel::class.java) }

    private var oldDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            oldDate = it.getString(OLD_DATE)
        }
    }

    /*
    * show() 직후, 화면회전 직후에 호출됨.
    * */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG, "onCreateDialog()")
        val date = try {
            LocalDate.parse(oldDate)
        } catch (e: DateTimeParseException) {
            throw StringFormException(
                    AccountBookApplication.applicationContext().resources.getString(
                            R.string.date))
        }
        return DatePickerDialog(requireContext(), this, date.year, date.monthValue - 1, date.dayOfMonth)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Log.d(TAG, "onDateSet()")
        val newDate = LocalDate.of(year, month + 1, dayOfMonth)
        datePickerViewModel.setDate(newDate.toString())
    }

    companion object {
        private const val OLD_DATE = "oldDate"
        @JvmStatic
        fun newInstance(oldDate: String) =
                DatePickerFragment().apply {
                    arguments = Bundle().apply {
                        putString(OLD_DATE, oldDate)
                    }
                }
    }
}