package com.example.accountbookex3.data

import androidx.lifecycle.MutableLiveData
import java.time.LocalDate

class FormedRecord(
        date: LocalDate = LocalDate.now(), isIncome: Boolean = true, amount: String = "0") {
    val date: MutableLiveData<LocalDate> = MutableLiveData()
    val isIncome: MutableLiveData<Boolean> = MutableLiveData()
    val amount: MutableLiveData<String> = MutableLiveData()

    init {
        setDate(date)
        setIsIncome(isIncome)
        setAmount(amount)
    }

    fun setDate(newDate: LocalDate) {
        date.value = newDate
    }
    fun setIsIncome(newIsIncome: Boolean) {
        isIncome.value = newIsIncome
    }
    fun setAmount(newAmount: String) {
        amount.value = newAmount
    }
    fun getDate(): LocalDate = date.value!!
    fun getIsIncome(): Boolean = isIncome.value!!
    fun getAmount(): String = amount.value!!

    override fun toString(): String =
            "${getDate()}, ${if (getIsIncome()) "수입" else "지출"}: ${getAmount()}"
}