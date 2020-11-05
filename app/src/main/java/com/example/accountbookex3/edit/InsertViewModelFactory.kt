package com.example.accountbookex3.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.database.DbViewModel
import java.lang.IllegalArgumentException

/*
* https://readystory.tistory.com/176
* 의 4번 방법 이용해서 만듦
* */
class InsertViewModelFactory(private val dbViewModel: DbViewModel): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(InsertViewModel::class.java)) {
            InsertViewModel(dbViewModel) as T
        }
        else {
            throw IllegalArgumentException()
        }
    }

}