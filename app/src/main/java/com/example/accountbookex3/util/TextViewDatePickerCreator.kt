package com.example.accountbookex3.util

import android.util.Log
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

class TextViewDatePickerCreator
(private val activity: FragmentActivity, private val tag: String, private val tvDate: TextView) {
    private val TAG = "TextViewDatePickerCreatorLog"
    private val datePickerFragment = DatePickerFragment() // 하나 만들어놓고 갖고있기
    init {
        datePickerFragment.apply {
            attachedTextView = tvDate
            /*
            * 달력 띄운 상태에서 회전시켰을 때 살아남는 설정
            * 아래 주소 참고:
            * https://stackoverflow.com/questions/14657490/how-to-properly-retain-a-dialogfragment-through-rotation
            * */
            retainInstance = true
        }
    }

    fun showDatePickerDialog() {
        Log.d(TAG, "showDatePickerDialog()")
        try {
            datePickerFragment.show(activity.supportFragmentManager, tag)
        } catch (e: NullPointerException) {
            Log.d(TAG, "TextView를 지정해주세요.")
        }
    }
}
