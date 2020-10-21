package com.example.accountbookex3.test

import android.util.Log
import com.example.accountbookex3.data.DateRecord
import com.example.accountbookex3.data.FormedRecord
import com.example.accountbookex3.model.Repository
import io.realm.RealmResults

/*
* 이 클래스는 테스트 목적으로만 만들어졌으며 실제로 사용할 때는 아예 없어도 되게 만들어져있음.
* */
class RepositoryTest(val repository: Repository) {
    private val TAG = "RepositoryTestLog"

    fun test() {
/*
        Log.d(TAG, "test()")
        val formedRecord = FormedRecord(
            LocalDate.of(2020, 10, 6), false, 5600)
        insert(formedRecord)
        printResults(selectAll())
*/
    }

    private fun insert(formedRecord: FormedRecord) {
        Log.d(TAG, "insert()")
        repository.insert(formedRecord)
    }

    private fun selectAll(): RealmResults<DateRecord> {
        Log.d(TAG, "selectAll()")
        return repository.selectAll()
    }

    private fun printResults(results: RealmResults<DateRecord>) {
        Log.d(TAG, "printResults(results)")
        for (dateRecord in results) {
            Log.d(TAG, dateRecord.toString())
        }
    }
}