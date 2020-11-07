package com.example.accountbookex3.viewmodel

import android.widget.TextView
import androidx.lifecycle.ViewModel

/*
* TextView를 보관하고있다가 text를 세팅해주는 역할.
* DatePickerHelper와 같이 사용함
* */
class DatePickerViewModel: ViewModel() {
    var textView: TextView? = null

    fun setTextView(newDate: String) {
        textView?.text = newDate
    }
}