package com.example.accountbookex3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.accountbookex3.R
import com.example.accountbookex3.viewmodel.EditViewModel

abstract class EditFragment: DialogFragment() {
    protected abstract val editViewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 프래그먼트 전체화면으로 설정
        // https://stackoverflow.com/questions/7189948/full-screen-dialogfragment-in-android 참고
        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachFragment()
    }

    protected abstract fun attachFragment()
}