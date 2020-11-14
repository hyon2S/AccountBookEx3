package com.example.accountbookex3.viewmodel

import androidx.lifecycle.ViewModel
import com.example.accountbookex3.data.InputFormData

class InsertViewModel(val dbViewModel: DbViewModel): ViewModel() {
    var inputFormData = InputFormData()

    fun initFormedRecord() {
        inputFormData = InputFormData() // 새 걸로 만들어서 바꿔끼움.
    }

    fun insert() {
        dbViewModel.insert(inputFormData)
    }

    // 전부 지워도 됨. 일단 주석처리하겠음.
/*
    fun setDate(newDate: String) {
        inputFormData.setDate(newDate)
    }
    fun setIsIncome(newIsIncome: Boolean) {
        inputFormData.setIsIncome(newIsIncome)
    }
    fun setAmount(newAmount: String) {
        inputFormData.setAmount(newAmount)
    }
    fun getDate(): String = inputFormData.getDate()
    fun getIsIncome(): Boolean = inputFormData.getIsIncome()
    fun getAmount(): String = inputFormData.getAmount()
*/
}