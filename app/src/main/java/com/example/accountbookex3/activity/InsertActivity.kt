package com.example.accountbookex3.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.datepicker.DatePickerFragment
import com.example.accountbookex3.datepicker.DatePickerHelper
import com.example.accountbookex3.exception.RecordFormException
import com.example.accountbookex3.fragment.InsertButtonFragment
import com.example.accountbookex3.fragment.InsertFormFragment
import com.example.accountbookex3.viewmodel.DatePickerViewModel
import com.example.accountbookex3.viewmodel.DbViewModel
import com.example.accountbookex3.viewmodel.InsertViewModel
import com.example.accountbookex3.viewmodel.InsertViewModelFactory

class InsertActivity : AppCompatActivity(), DatePickerHelper {
    private val TAG = "InsertActivityLog"

    private val dbViewModel by lazy { ViewModelProvider(this).get(DbViewModel::class.java) }
    private lateinit var insertViewModel: InsertViewModel

    private val DATE_PICKER_FRAG_TAG = "datePickerTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        // attachFragment()에서 InsertActivity에 붙일 Fragment를 만들기전에 insertViewModel이 이미 만들어져야함
        // lazy 초기화하지말것
        insertViewModel = ViewModelProvider(this, InsertViewModelFactory(dbViewModel)).get(InsertViewModel::class.java)

        attachFragment()
        Log.d(TAG, "fragment 붙임")
    }

    // 취소버튼 누르면 일어나는 일
    fun cancel() {
        Log.d(TAG, "cancel()")
        finish()
    }

    // db에 새 데이터 추가함
    fun insert() {
        Log.d(TAG, "confirm()")

        Log.d(TAG, insertViewModel.formedRecord.toString())
        // viewmodel 이용해서 insert하기
        Log.d(TAG, "dbViewModel.insert()")
        try {
            insertViewModel.insert()

            Toast.makeText(this, getString(R.string.insert_done_message), Toast.LENGTH_SHORT).show()
            finish()
        } catch (e: RecordFormException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun attachFragment() {
        Log.d(TAG, "attachFragment()")
        val insertFormFragment: InsertFormFragment =
            supportFragmentManager.findFragmentById(R.id.csl_form) as InsertFormFragment? ?: InsertFormFragment()
        val btnFragment: InsertButtonFragment =
            supportFragmentManager.findFragmentById(R.id.csl_buttons) as InsertButtonFragment? ?: InsertButtonFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.csl_form, insertFormFragment)
            .replace(R.id.csl_buttons, btnFragment)
            .commit()
        Log.d(TAG, "프래그먼트 붙임")
    }

    // DatePickerHelper 구현 항목들
    override val datePickerViewModel by lazy { ViewModelProvider(this).get(DatePickerViewModel::class.java) }

    override fun chooseDate(textView: TextView) {
        datePickerViewModel.textView = textView // 화면 회전하면 일반 변수는 초기화되니까 뷰모델에 저장해둠
        val oldDate: String = textView.text.toString()
        DatePickerFragment.newInstance(oldDate).show(supportFragmentManager, DATE_PICKER_FRAG_TAG)
    }
}