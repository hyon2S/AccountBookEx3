package com.example.accountbookex3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

/*
* DateFragment에 표시할 기간을 관리.
* 기간: fromDate ~ toDate
* */
class RvViewModel: ViewModel() {
    val fromDate: MutableLiveData<LocalDate> = MutableLiveData()
    val toDate: MutableLiveData<LocalDate> = MutableLiveData()

    init {
        fromDate.value = LocalDate.now()
                .minusMonths(1).plusDays(1)
        toDate.value = LocalDate.now()
    }

    fun getFromDate(): LocalDate = fromDate.value!!
    fun setFromDate(date: LocalDate) {
        fromDate.value = date
    }
    fun getToDate(): LocalDate = toDate.value!!
    fun setToDate(date: LocalDate) {
        toDate.value = date
    }
}