package com.example.accountbookex3.data

import androidx.lifecycle.MutableLiveData

class TotalAmounts {
    val totalIncome: MutableLiveData<Int> = MutableLiveData(0)
    val totalOutcome: MutableLiveData<Int> = MutableLiveData(0)
    val totalAmount: MutableLiveData<Int> = MutableLiveData(0)

    fun setTotalAmounts(income: Int, outcome: Int) {
        totalIncome.value = income
        totalOutcome.value = outcome
        totalAmount.value = income - outcome
    }
}