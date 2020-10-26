package com.example.accountbookex3.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.activity.InsertActivity
import com.example.accountbookex3.databinding.FragmentInsertFormBinding
import com.example.accountbookex3.util.TextViewDatePickerCreator
import com.example.accountbookex3.viewmodel.DbViewModel
import com.example.accountbookex3.viewmodel.InsertViewModel
import com.example.accountbookex3.viewmodel.InsertViewModelFactory

class InsertFormFragment : Fragment() {
    private val TAG = "InsertFormFragmentLog"

    private val attachedActivity by lazy { activity as InsertActivity }

    private val dbViewModel by lazy { ViewModelProvider(requireActivity()).get(DbViewModel::class.java) }
    private val insertViewModel by lazy { ViewModelProvider(requireActivity(), InsertViewModelFactory(dbViewModel)).get(InsertViewModel::class.java) }

    private lateinit var binding: FragmentInsertFormBinding

    private val datePickerCreator by lazy { TextViewDatePickerCreator(requireActivity(), "datePicker") }

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
            datePickerCreator.showDatePickerDialog()
        }
        datePickerCreator.tvDate = binding.tvDate
    }
}
