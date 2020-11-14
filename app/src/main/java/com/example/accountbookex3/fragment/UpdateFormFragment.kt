package com.example.accountbookex3.fragment

import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.viewmodel.UpdateViewModel

class UpdateFormFragment : EditFormFragment() {
    override val editViewModel by lazy { ViewModelProvider(requireActivity()).get(UpdateViewModel::class.java) }
}
