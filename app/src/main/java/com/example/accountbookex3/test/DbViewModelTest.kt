package com.example.accountbookex3.test

import android.util.Log
import com.example.accountbookex3.data.DateRecord
import com.example.accountbookex3.data.InputFormData
import com.example.accountbookex3.viewmodel.DbViewModel
import io.realm.RealmResults
import java.time.LocalDate

/*
* 이 클래스는 테스트 목적으로만 만들어졌으며 실제로 사용할 때는 아예 없어도 되게 만들어져있음.
* */
class DbViewModelTest(val dbViewModel: DbViewModel) {
    private val TAG = "DbViewModelTestLog"

    fun test() {
        Log.d(TAG, "test()")
/*
        val inputFormData = InputFormData(
            LocalDate.of(2020, 8, 3), false, 300)
        insert(inputFormData)
        printResults(selectAll())
*/
        // move("2020-10-18", 0, 1)
        Log.d(TAG, "test완료")
    }

    private fun move(date: LocalDate, fromPosition: Int, toPosition: Int) {
        Log.d(TAG, "move(${date}: ${fromPosition} -> ${toPosition})")
        dbViewModel.moveRecord(date, fromPosition, toPosition)
    }

    private fun delete(date: LocalDate, index: Int) {
        Log.d(TAG, "delete()")
        dbViewModel.delete(date, index)
    }

    private fun insert(inputFormData: InputFormData) {
        Log.d(TAG, "insert()")
        dbViewModel.insert(inputFormData)
    }

    private fun selectAll(): RealmResults<DateRecord> {
        Log.d(TAG, "selectAll()")
        return dbViewModel.selectAll()
    }

    private fun printResults(results: RealmResults<DateRecord>) {
        Log.d(TAG, "printResults(results)")
        for (dateRecord in results) {
            Log.d(TAG, dateRecord.toString())
        }
    }
}