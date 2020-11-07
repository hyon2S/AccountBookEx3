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
import com.example.accountbookex3.databinding.FragmentDateBinding
import com.example.accountbookex3.datepicker.DatePickerHelper
import com.example.accountbookex3.viewmodel.DateViewModel

/*
* 어느 날짜부터 어느 날짜까지의 내역을 표시할지 보여줌.
* 0000-00-00 ~ 0000-00-00의 형식으로 표시됨.
* 시작 날짜와 끝 날짜는 dateViewModel이 보관.
* */
class DateFragment : Fragment() {
    private val TAG = "DateFragmentLog"

    private val dateViewModel by lazy { ViewModelProvider(requireActivity()).get(DateViewModel::class.java) }

    private lateinit var binding: FragmentDateBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_date, container, false)
        return binding.root
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dateViewModel = dateViewModel
        // executePendingBindings 없으면 회전하면 화면 초기화 됨.
        binding.executePendingBindings()
        binding.lifecycleOwner = viewLifecycleOwner
        Log.d(TAG, "데이터바인딩 세팅")

        val datePickListener = View.OnClickListener{
            Log.d(TAG, "tvDate click")
            (activity as DatePickerHelper).chooseDate(it as TextView)
        }

        binding.tvFromDate.setOnClickListener(datePickListener)
        binding.tvToDate.setOnClickListener(datePickListener)
    }
}