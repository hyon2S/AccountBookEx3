package com.example.accountbookex3.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.accountbookex3.R
import com.example.accountbookex3.edit.InsertButtonAdapter
import kotlinx.android.synthetic.main.fragment_insert_button.*

class InsertButtonFragment : Fragment() {
    private val TAG = "InsertButtonFragmentLog"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView()")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insert_button, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // OnClickListener 설정
        btn_cancel.setOnClickListener { cancel() }
        btn_confirm.setOnClickListener { insert() }
    }

    private fun cancel() {
        Log.d(TAG, "cancel()")
        (parentFragment as InsertButtonAdapter).cancel()
    }
    private fun insert() {
        Log.d(TAG, "insert()")
        (parentFragment as InsertButtonAdapter).insert()
    }
}