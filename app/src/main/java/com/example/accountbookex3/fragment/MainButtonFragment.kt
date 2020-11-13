package com.example.accountbookex3.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.accountbookex3.R
import com.example.accountbookex3.edit.EditFragmentStartHelper
import kotlinx.android.synthetic.main.fragment_main_button.*

/*
* 버튼 누르면 현재 프래그먼트가 붙어있는 액티비티한테 새 액티비티를 띄우도록 요청함.
* */
class MainButtonFragment : Fragment() {
    private val TAG = "ButtonFragmentLog"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView()")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_button, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated()")
        super.onActivityCreated(savedInstanceState)
        btn_insert.setOnClickListener { startInsertActivity() }
    }

    private fun startInsertActivity() {
        Log.d(TAG, "startInsertActivity()")
        (activity as EditFragmentStartHelper).startInsertFragment()
    }
}