package com.example.accountbookex3.edit

import java.time.LocalDate

/*
* update기능, delete기능 정의
* */
interface EditHelper {
    /*
    * 날짜 date의 index번째 Record를 수정하는 UpdateActivity를 띄우는 기능
    * */
    fun startUpdate(date: LocalDate, index: Int)

    fun startDelete(date: LocalDate, index: Int)
}