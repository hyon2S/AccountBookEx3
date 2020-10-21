package com.example.accountbookex3.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.accountbookex3.R
import com.example.accountbookex3.data.Record
import com.example.accountbookex3.databinding.RecyclerViewRecordBinding
import com.example.accountbookex3.dragandswipe.ItemTouchHelperAdapter
import com.example.accountbookex3.viewmodel.DbViewModel
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class RecordRvAdapter(val date: String, data: OrderedRealmCollection<Record>, val dbViewModel: DbViewModel):
    RealmRecyclerViewAdapter<Record, RecordRvViewHolder>(data, true, true)
    , ItemTouchHelperAdapter
{
    private val TAG = "RecordRvAdapterLog"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordRvViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerViewRecordBinding = DataBindingUtil.inflate(inflater, R.layout.recycler_view_record, parent, false)
        return RecordRvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordRvViewHolder, position: Int) {
        val record: Record = data?.get(position) ?: return
        holder.bind(record)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Log.d(TAG, "onItemMove(${date}: ${fromPosition} -> ${toPosition})")
        // dbViewModel.moveRecord(date, fromPosition, toPosition)
    }

    override fun onItemDeleteCheck(position: Int) {
        Log.d(TAG, "onItemDeleteCheck(${date}: ${position})")
        // dbViewModel.delete(date, position)
        // RealmRv는 notify가 알아서 됨.
    }
}