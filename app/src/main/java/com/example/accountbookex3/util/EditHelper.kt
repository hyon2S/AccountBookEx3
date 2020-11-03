package com.example.accountbookex3.util

/*
* update기능, delete기능 정의
* */
interface EditHelper {
    /*
    * 날짜 date의 index번째 Record를 수정하는 UpdateActivity를 띄우는 기능
    * */
    fun startUpdate(date: String, index: Int)

    fun startDelete(date: String, index: Int)
}