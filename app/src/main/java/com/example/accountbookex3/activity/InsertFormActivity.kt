package com.example.accountbookex3.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.databinding.ActivityInsertFormBinding
import com.example.accountbookex3.exception.RecordFormException
import com.example.accountbookex3.fragment.InsertButtonFragment
import com.example.accountbookex3.util.TextViewDatePickerCreator
import com.example.accountbookex3.viewmodel.DbViewModel
import com.example.accountbookex3.viewmodel.InsertViewModel
import com.example.accountbookex3.viewmodel.InsertViewModelFactory

class InsertFormActivity : AppCompatActivity() {
    private val TAG = "InsertFormActivityLog"

    private val datePickerCreator = TextViewDatePickerCreator(this, "datePicker")

    private val dbViewModel by lazy { ViewModelProvider(this).get(DbViewModel::class.java) }
    private val insertViewModel by lazy { ViewModelProvider(this, InsertViewModelFactory(dbViewModel)).get(InsertViewModel::class.java) }
    private val binding: ActivityInsertFormBinding by lazy {
        DataBindingUtil.setContentView<ActivityInsertFormBinding>(this, R.layout.activity_insert_form)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_form)

        attachFragment()
        Log.d(TAG, "버튼fragment 붙임")

        binding.formedRecord = insertViewModel.formedRecord
        // executePendingBindings 없으면 회전하면 화면 초기화 됨.
        binding.executePendingBindings()
        binding.lifecycleOwner = this
        Log.d(TAG, "데이터바인딩 세팅")

        datePickerCreator.tvDate = binding.tvDate

        binding.tvDate.setOnClickListener {
            datePickerCreator.showDatePickerDialog()
        }
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
//            dbViewModel.insert(insertViewModel.formedRecord)

            Toast.makeText(this, getString(R.string.insert_done_message), Toast.LENGTH_SHORT).show()
            finish()
        } catch (e: RecordFormException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun attachFragment() {
        Log.d(TAG, "attachFragment()")
        val btnFragment: InsertButtonFragment =
            supportFragmentManager.findFragmentById(R.id.csl_buttons) as InsertButtonFragment? ?: InsertButtonFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.csl_buttons, btnFragment)
            .commit()
        Log.d(TAG, "프래그먼트 붙임")
    }
}