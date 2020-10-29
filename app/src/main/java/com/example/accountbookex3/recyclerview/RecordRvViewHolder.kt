package com.example.accountbookex3.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.accountbookex3.data.Record
import com.example.accountbookex3.databinding.RecyclerViewRecordBinding

class RecordRvViewHolder(private val binding: RecyclerViewRecordBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(record: Record) {
        binding.record = record // 데이터바인딩
    }
}