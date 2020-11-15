package com.example.accountbookex3.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.accountbookex3.data.DateRecord
import com.example.accountbookex3.data.InputFormData
import com.example.accountbookex3.data.RecordInfo
import com.example.accountbookex3.recyclerview.DateRvAdapter
import io.realm.RealmResults
import java.time.LocalDate

/*
* 내역에 표시할 날짜와 RecyclerView Adapter를 관리
* insert, update, delete 기능을 통해 데이터의 내용이 바뀌거나, 내역을 보여 줄 기간이 바뀌면
* setAdapterData()를 이용해 data를 새로 받아오고 adapter에 notify해줌
* */
class RvViewModel(val dbViewModel: DbViewModel): ViewModel() {
    private val TAG = "RvViewModelLog"

    // 화면에 표시할 내역의 기간 관리: fromDate ~ toDate
    val fromDate: MutableLiveData<LocalDate> = MutableLiveData()
    val toDate: MutableLiveData<LocalDate> = MutableLiveData()

    init {
        fromDate.value = LocalDate.now()
                .minusMonths(1).plusDays(1)
        toDate.value = LocalDate.now()
    }

    fun getFromDate(): LocalDate = fromDate.value!!
    fun setFromDate(date: LocalDate) {
        fromDate.value = date
        setAdapterData()
    }
    fun getToDate(): LocalDate = toDate.value!!
    fun setToDate(date: LocalDate) {
        toDate.value = date
        setAdapterData()
    }

    // 여기부터는 RecyclerView Adapter 관리
    var adapter: DateRvAdapter? = null
        set(value) {
            field = value
            setAdapterData()
        }

    private fun setAdapterData() {
        Log.d(TAG, "setAdapterData()")
        adapter?.data = selectBetween()
        adapter?.notifyDataSetChanged()
    }

    private fun selectBetween(): RealmResults<DateRecord> =
            dbViewModel.selectBetween(getFromDate(), getToDate())

    // RecyclerView에서 db를 변경하는 기능들
    fun insert(inputFormData: InputFormData) {
        Log.d(TAG, "insert()")
        dbViewModel.insert(inputFormData)
        setAdapterData()
    }

    fun update(recordInfo: RecordInfo, inputFormData: InputFormData) {
        Log.d(TAG, "update()")
        dbViewModel.update(recordInfo, inputFormData)
        setAdapterData()
    }

    fun delete(recordInfo: RecordInfo) {
        Log.d(TAG, "delete()")
        dbViewModel.delete(recordInfo)
        setAdapterData()
    }
}