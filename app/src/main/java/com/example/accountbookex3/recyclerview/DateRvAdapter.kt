package com.example.accountbookex3.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.accountbookex3.R
import com.example.accountbookex3.data.DateRecord
import com.example.accountbookex3.databinding.RecyclerViewDateBinding
import com.example.accountbookex3.edit.RvEditHelper
import io.realm.OrderedRealmCollection

class DateRvAdapter(private val rvEditHelper: RvEditHelper)
    : RecyclerView.Adapter<DateRvViewHolder>() {
    lateinit var data: OrderedRealmCollection<DateRecord>

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateRvViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerViewDateBinding = DataBindingUtil.inflate(inflater, R.layout.recycler_view_date, parent, false)
        return DateRvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateRvViewHolder, position: Int) {
        val dateRecord = data.get(position) ?: return
        holder.bind(dateRecord, rvEditHelper)
    }
}