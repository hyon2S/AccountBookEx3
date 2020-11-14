package com.example.accountbookex3.viewmodel

import androidx.lifecycle.ViewModel
import com.example.accountbookex3.data.InputFormData
/*
* sub classes: InsertViewModel, UpdateViewModel
* */
abstract class EditViewModel(val dbViewModel: DbViewModel): ViewModel() {
    var inputFormData: InputFormData = InputFormData()

    abstract fun initFormedRecord()
}