package com.example.accountbookex3.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.accountbookex3.data.FormedRecord
import com.example.accountbookex3.data.Record
import com.example.accountbookex3.data.RecordInfo
import java.time.LocalDate

/*
 * UpdateActivity의 데이터들을 관리하는 역할을 함.
 * 수정하는 화면에서 날짜, 금액 등을 수정하면 변경되는 정보들이 formedRecord에 반영되게 함.
 * dbViewModel을 통해 갖고있는 정보를 db에 update하는 기능까지 가짐.
 */
class UpdateViewModel(val dbViewModel: DbViewModel): ViewModel() {
    private val TAG = "UpdateViewModelLog"

    var formedRecord: FormedRecord = FormedRecord()

    // 어느 날짜(date)의 몇 번째(index) 레코드를 수정하는지 정보 저장.
    var recordInfo: RecordInfo? = null
        set(value) {
            Log.d(TAG, "set updateData")
            field = value
            if (value != null)
                initFormedRecord()
        }

    private fun initFormedRecord() {
        val record: Record = dbViewModel.select(recordInfo!!.date, recordInfo!!.index)
        formedRecord = FormedRecord(recordInfo!!.date, record.isIncome, record.amount.toString())
    }

    fun update() {
        Log.d(TAG, "update()")
        dbViewModel.update(recordInfo!!.date, recordInfo!!.index, formedRecord)
    }
}