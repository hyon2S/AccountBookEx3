package com.example.accountbookex3.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.data.DateRecord
import com.example.accountbookex3.edit.EditFragmentStartHelper
import com.example.accountbookex3.recyclerview.DateRvAdapter
import com.example.accountbookex3.edit.RvEditHelper
import com.example.accountbookex3.viewmodel.DbViewModel
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import java.time.LocalDate

class RecyclerViewFragment : Fragment(), RvEditHelper {
    private val TAG = "RecyclerViewFragmentLog"

    private val dbViewModel by lazy { ViewModelProvider(requireActivity()).get(DbViewModel::class.java) }
    lateinit var data: RealmResults<DateRecord>

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView()")
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated()")
        data = dbViewModel.selectAll()
        Log.d(TAG, "viewModel selectAll()로 data 얻어옴")
        initRecyclerView()
    }

    private fun initRecyclerView() {
        Log.d(TAG, "initRecyclerView()")
        rv_outer.apply {
            setHasFixedSize(true)
            adapter = DateRvAdapter(data, dbViewModel, this@RecyclerViewFragment)
        }
    }

    override fun startUpdate(date: LocalDate, index: Int) {
        (activity as EditFragmentStartHelper).startUpdateFragment(date, index)
    }

    override fun startDelete(date: LocalDate, index: Int) {
        Log.d(TAG, "startDelete()")
        (activity as EditFragmentStartHelper).startDeleteFragment(date, index)
    }
}