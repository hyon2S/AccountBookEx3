package com.example.accountbookex3.edit

/*
* 삭제하는 기능을 정의.
* DeleteAlertDialogFragment와 함께 사용
* */
interface DeleteDialogHelper {
    fun delete(date: String, index: Int)
}