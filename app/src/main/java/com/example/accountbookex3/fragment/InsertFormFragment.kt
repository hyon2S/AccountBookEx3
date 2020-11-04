package com.example.accountbookex3.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.databinding.FragmentInsertFormBinding
import com.example.accountbookex3.datepicker.DatePickerHelper
import com.example.accountbookex3.viewmodel.InsertViewModel

class InsertFormFragment : Fragment() {
    private val TAG = "InsertFormFragmentLog"

    private val insertViewModel by lazy { ViewModelProvider(requireActivity()).get(InsertViewModel::class.java) }

    private lateinit var binding: FragmentInsertFormBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_insert_form, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.formedRecord = insertViewModel.formedRecord
        // executePendingBindings 없으면 회전하면 화면 초기화 됨.
        binding.executePendingBindings()
        binding.lifecycleOwner = viewLifecycleOwner
        Log.d(TAG, "데이터바인딩 세팅")

        binding.tvDate.setOnClickListener {
            Log.d(TAG, "tvDate click")
            (activity as DatePickerHelper).chooseDate(it as TextView)
        }
    }
}
