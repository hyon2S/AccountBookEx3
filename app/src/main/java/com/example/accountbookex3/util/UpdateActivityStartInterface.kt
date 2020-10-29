package com.example.accountbookex3.util

/*
* UpdateActivity를 start하는 기능을 정의
* */
interface UpdateActivityStartInterface {
    /*
    * 날짜 date의 index번째 Record를 수정하는 UpdateActivity를 띄우는 기능
    * */
    fun startUpdateActivity(date: String, index: Int)
}