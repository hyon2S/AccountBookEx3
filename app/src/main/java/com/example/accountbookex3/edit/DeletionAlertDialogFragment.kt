package com.example.accountbookex3.edit

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.accountbookex3.R

/*
* https://developer.android.com/guide/topics/ui/dialogs
* 참고해서 만듦
* Fragment는 기본생성자 써야된다고 하는데, Fragment한테 db 접근 권한을 줄 게 아니면 매개변수 없이 하는 방법을 도저히 모르겠어서...
* 호출하는 쪽에서 retainInstance = true 설정 하고 매개변수로 전달하는 것으로 함.
* */
class DeletionAlertDialogFragment(): DialogFragment() {
    private var date: String? = null
    private var index: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            date = it.getString(DATE)
            index = it.getInt(INDEX)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.confirm_delete)
                    .setPositiveButton(R.string.yes) { _, _ ->
                        (activity as DeleteDialogHelper).delete(date!!, index!!)
                    }
                    .setNegativeButton(R.string.no, null) // null해도 되는거 맞지?
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        private const val DATE = "date"
        private const val INDEX = "index"

        @JvmStatic
        fun newInstance(date: String, index: Int) =
                DeletionAlertDialogFragment().apply {
                    arguments = Bundle().apply {
                        putString(DATE, date)
                        putInt(INDEX, index)
                    }
                }
    }
}