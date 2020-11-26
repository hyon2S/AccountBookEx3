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
import com.example.accountbookex3.databinding.FragmentTotalAmountsBinding
import com.example.accountbookex3.viewmodel.RvViewModel

/*
* 해당 기간 동안의 총수입, 총지출, 총합(총수입 - 총지출)을 표시
* */
class TotalAmountsFragment : Fragment() {
    private val TAG = "TotalAmountFragmentLog"

    private val rvViewModel by lazy { ViewModelProvider(requireActivity()).get(RvViewModel::class.java) }

    private lateinit var binding: FragmentTotalAmountsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_total_amounts, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.totalamounts = rvViewModel.totalAmounts
        // executePendingBindings 없으면 회전하면 화면 초기화 됨.
        binding.executePendingBindings()
        binding.lifecycleOwner = viewLifecycleOwner
        Log.d(TAG, "데이터바인딩 세팅")
    }
}