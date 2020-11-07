package com.example.accountbookex3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

/*
* DateFragment에 표시할 기간을 관리.
* 기간: fromDate ~ toDate
* */
class DateViewModel: ViewModel() {
    val fromDate: MutableLiveData<String> = MutableLiveData()
    val toDate: MutableLiveData<String> = MutableLiveData()

    init {
        fromDate.value = LocalDate.now()
                .minusMonths(1).plusDays(1).toString()
        toDate.value = LocalDate.now().toString()
    }
}