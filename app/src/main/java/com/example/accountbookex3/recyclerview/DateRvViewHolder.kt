package com.example.accountbookex3.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.accountbookex3.data.DateRecord
import com.example.accountbookex3.databinding.RecyclerViewDateBinding
import com.example.accountbookex3.edit.RvEditHelper

class DateRvViewHolder(private val binding: RecyclerViewDateBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(dateRecord: DateRecord, rvEditHelper: RvEditHelper) {
        binding.dateRecord = dateRecord
        val recordAdapter = RecordRvAdapter(dateRecord.getDate(), dateRecord.list, rvEditHelper)
        binding.rvInner.apply {
            setHasFixedSize(true)
            adapter = recordAdapter
        }
        // 중첩구조니까 어쩔수없긴 한데 그래도 너무 심각하게 의존하는 구조가 아닌가 싶음..
    }
}