package com.example.accountbookex3.data

import androidx.lifecycle.MutableLiveData
import java.time.LocalDate

class FormedRecord() {
    val date: MutableLiveData<String> = MutableLiveData()
    val isIncome: MutableLiveData<Boolean> = MutableLiveData()
    val amount: MutableLiveData<String> = MutableLiveData()

    init {
        initProperties()
    }

    fun setDate(newDate: String) {
        date.value = newDate
    }
    fun setIsIncome(newIsIncome: Boolean) {
        isIncome.value = newIsIncome
    }
    fun setAmount(newAmount: String) {
        amount.value = newAmount
    }
    fun getDate(): String = date.value!!
    fun getIsIncome(): Boolean = isIncome.value!!
    fun getAmount(): String = amount.value!!

    private fun initProperties(
        date: String = LocalDate.now().toString(), isIncome: Boolean = true, amount: String = "0") {
        setDate(date)
        setIsIncome(isIncome)
        setAmount(amount)
    }

    override fun toString(): String =
        "${getDate()}, ${if (getIsIncome()) "수입" else "지출"}: ${getAmount()}"
}