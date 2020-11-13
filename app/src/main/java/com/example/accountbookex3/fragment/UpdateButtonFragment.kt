package com.example.accountbookex3.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.accountbookex3.R
import com.example.accountbookex3.edit.UpdateButtonAdapter
import kotlinx.android.synthetic.main.fragment_update_button.*

/*
* 취소, 저장 버튼이 있는 부분
* */
class UpdateButtonFragment : Fragment() {
    private val TAG = "UpdateButtonFragmentLog"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView()")
        return inflater.inflate(R.layout.fragment_update_button, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // OnClickListener 설정
        btn_cancel.setOnClickListener { cancel() }
        btn_update.setOnClickListener { update() }
    }

    private fun cancel() {
        Log.d(TAG, "cancel()")
        (parentFragment as UpdateButtonAdapter).cancel()
    }
    private fun update() {
        Log.d(TAG, "update()")
        (parentFragment as UpdateButtonAdapter).update()
    }
}