package com.example.accountbookex3.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.accountbookex3.data.DateRecord
import com.example.accountbookex3.data.InputFormData
import com.example.accountbookex3.data.Record
import com.example.accountbookex3.data.RecordInfo
import com.example.accountbookex3.database.Repository
import io.realm.Realm
import io.realm.RealmResults
import java.time.LocalDate

class DbViewModel: ViewModel() {
    private val TAG = "DbViewModelLog"

    private val realm: Realm = Realm.getDefaultInstance()
    private val repository = Repository(realm)

    fun insert(inputFormData: InputFormData) {
        realm.executeTransaction {
            repository.insert(inputFormData)
        }
    }

    fun update(recordInfo: RecordInfo, inputFormData: InputFormData) {
        realm.executeTransaction {
            repository.update(recordInfo, inputFormData)
        }
    }

    fun select(recordInfo: RecordInfo): Record =
            repository.select(recordInfo)

    fun selectBetween(fromDate: LocalDate, toDate: LocalDate): RealmResults<DateRecord> =
            repository.selectBetween(fromDate, toDate)

    fun selectAll(): RealmResults<DateRecord> =
            repository.selectAll()
    // 진짜? 리턴하지 않고 변수로 갖고있다가 나중에 Rv의 list에 넣는다든지 할 수도 있지 않을까?

    fun delete(recordInfo: RecordInfo) { // DateRecord와 Record자체를 변수로 받을수도 있을듯..?
        realm.executeTransaction {
            repository.delete(recordInfo)
        }
    }

    fun moveRecord(date: LocalDate, fromIndex: Int, toIndex: Int) {
        realm.executeTransaction {
            repository.moveRecord(date, fromIndex, toIndex)
        }
    }

    fun deleteAll() { // 초기화 목적으로 있는 것이므로 평소에는 절대 쓰지 않음.
        realm.executeTransaction {
            realm.deleteAll()
        }
    }

    // fromDate ~ toDate 기간 동안의 총수입/지출을 계산
    fun getTotalIncomeOutcomeBetween(fromDate: LocalDate, toDate: LocalDate, isIncome: Boolean): Int =
            repository.getTotalIncomeOutcomeBetween(fromDate, toDate, isIncome)

    override fun onCleared() {
        Log.d(TAG, "onCleared()")
        super.onCleared()
        realm.close()
        Log.d(TAG, "close realm")
    }
}