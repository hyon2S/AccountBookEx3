package com.example.accountbookex3.fragment

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.edit.InsertButtonAdapter
import com.example.accountbookex3.exception.RecordFormException
import com.example.accountbookex3.viewmodel.InsertViewModel

class InsertFragment : EditFragment(), InsertButtonAdapter {
    private val TAG = "InsertFragmentLog"

    override val editViewModel: InsertViewModel by lazy { ViewModelProvider(requireActivity()).get(
        InsertViewModel::class.java) }

    override fun attachFragment() {
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
            editViewModel.insert()
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