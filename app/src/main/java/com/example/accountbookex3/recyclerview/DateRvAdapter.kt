package com.example.accountbookex3.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.accountbookex3.R
import com.example.accountbookex3.data.DateRecord
import com.example.accountbookex3.databinding.RecyclerViewDateBinding
import com.example.accountbookex3.util.UpdateActivityStartInterface
import com.example.accountbookex3.viewmodel.DbViewModel
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class DateRvAdapter(data: OrderedRealmCollection<DateRecord>, val dbViewModel: DbViewModel, private val updateAdapter: UpdateActivityStartInterface):
    RealmRecyclerViewAdapter<DateRecord, DateRvViewHolder>(data, true, true) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateRvViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerViewDateBinding = DataBindingUtil.inflate(inflater, R.layout.recycler_view_date, parent, false)
        return DateRvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateRvViewHolder, position: Int) {
        val dateRecord = data?.get(position) ?: return
        holder.bind(dateRecord, dbViewModel, updateAdapter)
    }
}