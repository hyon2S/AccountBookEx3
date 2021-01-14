package com.example.accountbookex3.data

import androidx.lifecycle.MutableLiveData

/*
* 지정한 기간 동안의 수입/지출 내역을 보여줄 때,
* 지정한 기간 동안의 총수입/총지출/총수입지출차액..?을 보여줌.
* */
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