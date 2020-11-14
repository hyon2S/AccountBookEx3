package com.example.accountbookex3.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.viewmodel.DeleteViewModel

/*
* https://developer.android.com/guide/topics/ui/dialogs
* 참고해서 만듦
* */
class DeleteAlertDialogFragment(): DialogFragment() {
    val deleteViewModel: DeleteViewModel by lazy {ViewModelProvider(requireActivity()).get(DeleteViewModel::class.java) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.confirm_delete)
                    .setPositiveButton(R.string.yes) { _, _ ->
                        delete()
                    }
                    .setNegativeButton(R.string.no, null) // null해도 되는거 맞지?
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun delete() {
        deleteViewModel.delete()
        Toast.makeText(requireContext(), R.string.delete_done_message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = DeleteAlertDialogFragment()
    }
}