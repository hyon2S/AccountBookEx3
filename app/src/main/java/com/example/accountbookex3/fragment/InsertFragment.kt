package com.example.accountbookex3.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.edit.InsertButtonAdapter
import com.example.accountbookex3.exception.RecordFormException
import com.example.accountbookex3.viewmodel.InsertViewModel

class InsertFragment : DialogFragment(), InsertButtonAdapter {
    private val TAG = "InsertFragmentLog"

    private val insertViewModel: InsertViewModel by lazy { ViewModelProvider(requireActivity()).get(
        InsertViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 프래그먼트 전체화면으로 설정
        // https://stackoverflow.com/questions/7189948/full-screen-dialogfragment-in-android 참고
        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachFragment()
    }

    private fun attachFragment() {
        Log.d(TAG, "attachFragment()")
        val insertFormFragment: InsertFormFragment =
            childFragmentManager.findFragmentById(R.id.csl_form) as InsertFormFragment? ?: InsertFormFragment()
        val btnFragment: InsertButtonFragment =
            childFragmentManager.findFragmentById(R.id.csl_buttons) as InsertButtonFragment? ?: InsertButtonFragment()

        childFragmentManager.beginTransaction()
            .replace(R.id.csl_form, insertFormFragment)
            .replace(R.id.csl_buttons, btnFragment)
            .commit()
        Log.d(TAG, "프래그먼트 붙임")
    }

    override fun cancel() {
        Log.d(TAG, "cancel()")
        dismiss()
    }

    override fun insert() {
        Log.d(TAG, "insert()")
        try {
            insertViewModel.insert()
            Toast.makeText(requireContext(), getString(R.string.insert_done_message), Toast.LENGTH_SHORT).show()
            dismiss()
        } catch (e: RecordFormException) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = InsertFragment()
    }
}