package com.example.accountbookex3.edit

/*
* update화면의 버튼이 하는 cancel, update 요청을 받아서 실행하는 역할
* */
interface UpdateButtonAdapter {
    fun cancel()
    fun update()
}