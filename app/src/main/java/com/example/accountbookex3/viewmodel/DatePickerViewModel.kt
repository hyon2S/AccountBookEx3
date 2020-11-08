package com.example.accountbookex3.viewmodel

import android.widget.TextView
import androidx.lifecycle.ViewModel

/*
* TextView를 보관하고있다가 text를 세팅해주는 역할.
* DatePickerHelper와 같이 사용함
* */
class DatePickerViewModel: ViewModel() {
    var textView: TextView? = null

    /*
    * DatePickerFragment를 이용해 날짜를 선택하고나면
    * DatePickerFragment의 onDateSet에 의해 호출됨
    * */
    fun setTextView(newDate: String) {
        textView?.text = newDate
    }
}