package com.example.accountbookex3.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.accountbookex3.data.Record
import com.example.accountbookex3.databinding.RecyclerViewRecordBinding
import com.example.accountbookex3.util.EditHelper

class RecordRvViewHolder(private val binding: RecyclerViewRecordBinding
 , private val editHelper: EditHelper)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(record: Record, date: String, position: Int) {
        binding.record = record // 데이터바인딩
        binding.root.setOnClickListener {
            editHelper.startUpdate(date, position)
        }
    }
}