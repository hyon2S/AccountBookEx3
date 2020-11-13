package com.example.accountbookex3.data

import java.time.LocalDate

/*
* 어느 날짜(date)의 몇 번째(index) 레코드인지 정보 저장.
* */
data class RecordInfo(val date: LocalDate, val index: Int)