package com.example.accountbookex3.datepicker

import android.widget.TextView
import com.example.accountbookex3.viewmodel.DatePickerViewModel

/*
* TextView에 DatePickerDialog를 이용해서 날짜를 선택하고 다시 TextView의 text에 선택한 날짜를 set하는 것을 도와줌.
* datePickerViewModel은 TextView를 보관하고 text를 세팅해주는 역할을 함.
* 원래 일반 변수로 TextView를 보관하려고 했으나, 화면을 회전하면 TextView가 초기화되어버려 ViewModel을 사용함.
* */
interface DatePickerHelper {
    val datePickerViewModel: DatePickerViewModel

    fun chooseDate(textView: TextView)
}