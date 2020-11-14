package com.example.accountbookex3.fragment

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.edit.UpdateButtonAdapter
import com.example.accountbookex3.exception.RecordFormException
import com.example.accountbookex3.viewmodel.UpdateViewModel

class UpdateFragment : EditFragment(), UpdateButtonAdapter {
    private val TAG = "updateFragmentLog"

    override val editViewModel: UpdateViewModel by lazy { ViewModelProvider(requireActivity()).get(
            UpdateViewModel::class.java) }

    override fun attachFragment() {
        Log.d(TAG, "attachFragment()")
        val updateFormFragment: UpdateFormFragment =
                childFragmentManager.findFragmentById(R.id.csl_form) as UpdateFormFragment? ?: UpdateFormFragment()
        val btnFragment: UpdateButtonFragment =
                childFragmentManager.findFragmentById(R.id.csl_buttons) as UpdateButtonFragment? ?: UpdateButtonFragment()

        childFragmentManager.beginTransaction()
                .replace(R.id.csl_form, updateFormFragment)
                .replace(R.id.csl_buttons, btnFragment)
                .commit()
        Log.d(TAG, "프래그먼트 붙임")
    }

    override fun cancel() {
        Log.d(TAG, "cancel()")
        dismiss()
    }

    override fun update() {
        Log.d(TAG, "update()")
        try {
            editViewModel.update()
            Toast.makeText(requireContext(), getString(R.string.update_done_message), Toast.LENGTH_SHORT).show()
            dismiss()
        } catch (e: RecordFormException) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = UpdateFragment()
    }
}