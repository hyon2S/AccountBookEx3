package com.example.accountbookex3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.accountbookex3.R
import com.example.accountbookex3.databinding.FragmentInputFormBinding
import com.example.accountbookex3.datepicker.DatePickerHelper
import com.example.accountbookex3.viewmodel.EditViewModel
import java.time.LocalDate

/*
* 날짜 등등을 입력하는 입력 폼 부분.
* sub classes: InsertFormFragment, UpdateFromFragment
* */
abstract class EditFormFragment: Fragment() {
    protected abstract val editViewModel: EditViewModel

    private lateinit var binding: FragmentInputFormBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_input_form, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inputFormData = editViewModel.inputFormData
        // executePendingBindings 없으면 회전하면 화면 초기화 됨.
        binding.executePendingBindings()
        binding.lifecycleOwner = viewLifecycleOwner

        val callback: (LocalDate) -> Unit =
                { newDate -> editViewModel.inputFormData.setDate(newDate) }

        binding.tvDate.setOnClickListener {
            (activity as DatePickerHelper).chooseDate(editViewModel.inputFormData.getDate(), callback)
        }
    }
}