package com.example.accountbookex3.viewmodel

import androidx.lifecycle.ViewModel
import java.time.LocalDate

/*
* callback을 보관하고 있다가 DatePickerFragment가 onDateSet()을 하면 콜백을 수행함.
* DatePickerHelper와 같이 사용함
* */
class DatePickerViewModel: ViewModel() {
    var callback: ((LocalDate) -> Unit)? = null

    /*
    * DatePickerFragment를 이용해 날짜를 선택하고나면
    * DatePickerFragment의 onDateSet에 의해 호출됨
    * */
    fun setDate(newDate: LocalDate) {
        callback?.invoke(newDate)
    }
}