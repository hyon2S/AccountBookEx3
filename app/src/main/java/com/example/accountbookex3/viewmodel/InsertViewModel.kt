package com.example.accountbookex3.viewmodel

import androidx.lifecycle.ViewModel
import com.example.accountbookex3.data.FormedRecord

class InsertViewModel(val dbViewModel: DbViewModel): ViewModel() {
    val formedRecord = FormedRecord()

    // 전부 지워도 됨. 일단 주석처리하겠음.
/*
    fun setDate(newDate: String) {
        formedRecord.setDate(newDate)
    }
    fun setIsIncome(newIsIncome: Boolean) {
        formedRecord.setIsIncome(newIsIncome)
    }
    fun setAmount(newAmount: String) {
        formedRecord.setAmount(newAmount)
    }
    fun getDate(): String = formedRecord.getDate()
    fun getIsIncome(): Boolean = formedRecord.getIsIncome()
    fun getAmount(): String = formedRecord.getAmount()
*/
}