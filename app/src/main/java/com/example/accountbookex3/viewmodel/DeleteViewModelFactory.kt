package com.example.accountbookex3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/*
* https://readystory.tistory.com/176
* 의 4번 방법 이용해서 만듦
* */
class DeleteViewModelFactory(private val dbViewModel: DbViewModel)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DeleteViewModel::class.java)) {
            DeleteViewModel(dbViewModel) as T
        }
        else {
            throw IllegalArgumentException()
        }
    }
}