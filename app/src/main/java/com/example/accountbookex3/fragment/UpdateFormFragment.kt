package com.example.accountbookex3.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.databinding.FragmentInsertFormBinding
import com.example.accountbookex3.datepicker.DatePickerHelper
import com.example.accountbookex3.edit.UpdateViewModel

/*
* 날짜 등등을 입력하는 입력 폼 부분.
* InsertFormFragment와 InsertViewModel이 아니라 UpdateViewModel인 것 빼고는 완전 다 똑같음.
* 심지어 xml파일도 같은 것 사용
* 둘을 합칠 방법이 없을까...?
* */
class UpdateFormFragment : Fragment() {
    private val TAG = "UpdateFormFragmentLog"

    private val updateViewModel by lazy { ViewModelProvider(requireActivity()).get(UpdateViewModel::class.java) }

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
        binding.formedRecord = updateViewModel.formedRecord
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
