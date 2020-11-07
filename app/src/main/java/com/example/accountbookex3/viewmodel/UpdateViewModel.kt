package com.example.accountbookex3.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.accountbookex3.data.FormedRecord
import com.example.accountbookex3.data.Record

/*
 * UpdateActivity의 데이터들을 관리하는 역할을 함.
 * 어느 날짜(date)의 몇 번째(index) 레코드를 수정하는지 정보 저장.
 * 수정하는 화면에서 날짜, 금액 등을 수정하면 변경되는 정보들이 formedRecord에 반영되게 함.
 * dbViewModel을 통해 갖고있는 정보를 db에 update하는 기능까지 가짐.
 */
class UpdateViewModel(val dbViewModel: DbViewModel, val date: String, val index: Int): ViewModel() {
    private val TAG = "UpdateViewModelLog"

    val formedRecord: FormedRecord

    init {
        val record: Record = dbViewModel.select(date, index)
        formedRecord = FormedRecord(date, record.isIncome, record.amount.toString())
    }

    fun update() {
        Log.d(TAG, "update()")
        dbViewModel.update(date, index, formedRecord)
    }
}