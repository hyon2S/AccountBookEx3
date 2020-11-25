package com.example.accountbookex3.data

import androidx.lifecycle.MutableLiveData
import java.time.LocalDate

class InputFormData(
        date: LocalDate = LocalDate.now(), isIncome: Boolean = true, amount: String = "0", memo: String = "") {
    val date: MutableLiveData<LocalDate> = MutableLiveData()
    val isIncome: MutableLiveData<Boolean> = MutableLiveData()
    val amount: MutableLiveData<String> = MutableLiveData()
    val memo: MutableLiveData<String> = MutableLiveData()

    init {
        setDate(date)
        setIsIncome(isIncome)
        setAmount(amount)
        setMemo(memo)
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
    fun setMemo(newMemo: String) { memo.value = newMemo }

    fun getDate(): LocalDate = date.value!!
    fun getIsIncome(): Boolean = isIncome.value!!
    fun getAmount(): String = amount.value!!
    fun getMemo(): String = memo.value!!

    override fun toString(): String =
            "${getDate()}, ${if (getIsIncome()) "수입" else "지출"}: ${getAmount()}, 메모: ${getMemo()}"
}