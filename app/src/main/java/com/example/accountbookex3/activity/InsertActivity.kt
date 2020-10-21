package com.example.accountbookex3.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.databinding.ActivityInsertBinding
import com.example.accountbookex3.exception.RecordFormException
import com.example.accountbookex3.util.TextViewDatePickerCreator
import com.example.accountbookex3.viewmodel.DbViewModel
import com.example.accountbookex3.viewmodel.InsertViewModel
import kotlinx.android.synthetic.main.activity_insert.*

class InsertActivity : AppCompatActivity() {
    private val TAG = "InsertActivityLog"

    private val datePickerCreator = TextViewDatePickerCreator(this, "datePicker")

    private val insertViewModel by lazy { ViewModelProvider(this).get(InsertViewModel::class.java) }
    private val binding: ActivityInsertBinding by lazy {
        DataBindingUtil.setContentView<ActivityInsertBinding>(this, R.layout.activity_insert)
    }
    private val dbViewModel by lazy { ViewModelProvider(this).get(DbViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        binding.formedRecord = insertViewModel.formedRecord
        // executePendingBindings 없으면 회전하면 화면 초기화 됨.
        binding.executePendingBindings()
        binding.lifecycleOwner = this
        Log.d(TAG, "데이터바인딩 세팅")

        datePickerCreator.tvDate = binding.tvDate

        // OnClickListener 설정
        btn_cancel.setOnClickListener { cancel() }
        btn_confirm.setOnClickListener { confirm() }
        binding.tvDate.setOnClickListener {
            datePickerCreator.showDatePickerDialog()
        }
    }

    // 취소버튼 누르면 일어나는 일
    private fun cancel() {
        Log.d(TAG, "cancel()")
        finish()
    }

    // 확인버튼 누르면 일어나는 일
    private fun confirm() {
        Log.d(TAG, "confirm()")

        Log.d(TAG, insertViewModel.formedRecord.toString())
        // viewmodel 이용해서 insert하기
        Log.d(TAG, "dbViewModel.insert()")
        try {
            dbViewModel.insert(insertViewModel.formedRecord)

            Toast.makeText(this, getString(R.string.insert_done_message), Toast.LENGTH_SHORT).show()
            finish()
        } catch (e: RecordFormException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}