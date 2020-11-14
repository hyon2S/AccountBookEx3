package com.example.accountbookex3.viewmodel

import androidx.lifecycle.ViewModel
import com.example.accountbookex3.data.RecordInfo

class DeleteViewModel(val dbViewModel: DbViewModel): ViewModel() {
    // 삭제 할 Record의 날짜와 인덱스 정보를 저장
    var recordInfo: RecordInfo? = null
    fun delete() {
        dbViewModel.delete(recordInfo!!)
    }
}