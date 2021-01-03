package com.example.accountbookex3.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.accountbookex3.R
import com.example.accountbookex3.data.Record
import com.example.accountbookex3.databinding.RecyclerViewRecordBinding
import com.example.accountbookex3.edit.RvEditHelper
import io.realm.OrderedRealmCollection
import java.time.LocalDate

class RecordRvAdapter(val date: LocalDate, var data: OrderedRealmCollection<Record>, private val rvEditHelper: RvEditHelper)
    : RecyclerView.Adapter<RecordRvViewHolder>() {
    private val TAG = "RecordRvAdapterLog"

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordRvViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerViewRecordBinding = DataBindingUtil.inflate(inflater, R.layout.recycler_view_record, parent, false)
        return RecordRvViewHolder(binding, rvEditHelper)
    }

    override fun onBindViewHolder(holder: RecordRvViewHolder, position: Int) {
        val record: Record = data.get(position) ?: return
        holder.bind(record, date, position)
    }
}