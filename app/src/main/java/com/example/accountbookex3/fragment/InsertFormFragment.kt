package com.example.accountbookex3.fragment

import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.viewmodel.InsertViewModel

class InsertFormFragment : EditFormFragment() {
    override val editViewModel by lazy { ViewModelProvider(requireActivity()).get(InsertViewModel::class.java) }
}
