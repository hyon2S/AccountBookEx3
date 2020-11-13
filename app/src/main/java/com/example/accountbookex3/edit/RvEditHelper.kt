package com.example.accountbookex3.edit

import java.time.LocalDate

/*
* RecyclerView(Adapter, ViewHolder)로부터 update, delete 요청을 받아서 Activity, Fragment 등에 전달.
* 날짜 date의 index번째 레코드를 대상으로 함.
* */
interface RvEditHelper {
    fun startUpdate(date: LocalDate, index: Int)
    fun startDelete(date: LocalDate, index: Int)
}