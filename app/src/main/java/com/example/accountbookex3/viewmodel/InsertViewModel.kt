package com.example.accountbookex3.viewmodel

import androidx.lifecycle.ViewModel
import com.example.accountbookex3.data.FormedRecord

class InsertViewModel: ViewModel() {
    val formedRecord = FormedRecord()

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
}