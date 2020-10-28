package com.example.accountbookex3.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.exception.RecordFormException
import com.example.accountbookex3.fragment.UpdateButtonFragment
import com.example.accountbookex3.fragment.UpdateFormFragment
import com.example.accountbookex3.viewmodel.*

class UpdateActivity : AppCompatActivity() {
    private val TAG = "UpdateActivityLog"

    private val dbViewModel by lazy { ViewModelProvider(this).get(DbViewModel::class.java) }
    private lateinit var updateViewModel: UpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val date = intent.getStringExtra("date")
        // index를 받아오는 값이 없으면 오류가 나게 음수 값으로 함
        val index = intent.getIntExtra("index", -1)

        updateViewModel = ViewModelProvider(this, UpdateViewModelFactory(dbViewModel, date!!, index))
            .get(UpdateViewModel::class.java)

        attachFragment()
        Log.d(TAG, "fragment 붙임")
    }

    // 취소버튼 누르면 일어나는 일
    fun cancel() {
        Log.d(TAG, "cancel()")
        finish()
    }

    // db에서 기존 데이터를 변경함
    fun update() {
        Log.d(TAG, "update()")

        Log.d(TAG, updateViewModel.formedRecord.toString())
        // viewmodel 이용해서 update하기
        Log.d(TAG, "dbViewModel.update()")
        try {
            updateViewModel.update()

            Toast.makeText(this, getString(R.string.insert_done_message), Toast.LENGTH_SHORT).show()
            finish()
        } catch (e: RecordFormException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun attachFragment() {
        Log.d(TAG, "attachFragment()")
        val updateFormFragment: UpdateFormFragment =
            supportFragmentManager.findFragmentById(R.id.csl_form) as UpdateFormFragment? ?: UpdateFormFragment()
        val btnFragment: UpdateButtonFragment =
            supportFragmentManager.findFragmentById(R.id.csl_buttons) as UpdateButtonFragment? ?: UpdateButtonFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.csl_form, updateFormFragment)
            .replace(R.id.csl_buttons, btnFragment)
            .commit()
        Log.d(TAG, "프래그먼트 붙임")
    }
}