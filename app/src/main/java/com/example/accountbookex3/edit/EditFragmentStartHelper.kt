package com.example.accountbookex3.edit

import java.time.LocalDate

/*
* update, delete, insert 기능을 하는 Fragment를 띄우는 역할
* 날짜 date의 index번째 레코드를 대상으로 함. (insert제외)
* */
interface EditFragmentStartHelper {
    fun startUpdateFragment(date: LocalDate, index: Int)
    fun startDeleteFragment(date: LocalDate, index: Int)
    fun startInsertFragment()
}