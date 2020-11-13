package com.example.accountbookex3.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.accountbookex3.data.Record
import com.example.accountbookex3.databinding.RecyclerViewRecordBinding
import com.example.accountbookex3.edit.RvEditHelper
import java.time.LocalDate

class RecordRvViewHolder(private val binding: RecyclerViewRecordBinding
                         , private val rvEditHelper: RvEditHelper)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(record: Record, date: LocalDate, position: Int) {
        binding.record = record // 데이터바인딩
        binding.root.setOnClickListener {
            rvEditHelper.startUpdate(date, position)
        }
        binding.ivDelete.setOnClickListener {
            rvEditHelper.startDelete(date, position)
        }
    }
}