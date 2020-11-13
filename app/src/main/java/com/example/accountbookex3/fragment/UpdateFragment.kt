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
import com.example.accountbookex3.edit.UpdateButtonAdapter
import com.example.accountbookex3.exception.RecordFormException
import com.example.accountbookex3.viewmodel.UpdateViewModel

class UpdateFragment : DialogFragment(), UpdateButtonAdapter {
    private val TAG = "updateFragmentLog"

    private val updateViewModel: UpdateViewModel by lazy { ViewModelProvider(requireActivity()).get(
        UpdateViewModel::class.java) }

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
            updateViewModel.update()
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