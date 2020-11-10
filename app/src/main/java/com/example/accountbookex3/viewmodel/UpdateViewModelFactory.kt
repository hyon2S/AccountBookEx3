package com.example.accountbookex3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import java.time.LocalDate

/*
* https://readystory.tistory.com/176
* 의 4번 방법 이용해서 만듦
* */
class UpdateViewModelFactory
(private val dbViewModel: DbViewModel, private val date: LocalDate, private val index: Int)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UpdateViewModel::class.java)) {
            UpdateViewModel(dbViewModel, date, index) as T
        }
        else {
            throw IllegalArgumentException()
        }
    }

}