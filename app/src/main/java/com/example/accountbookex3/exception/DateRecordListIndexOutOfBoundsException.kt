package com.example.accountbookex3.exception

import java.lang.RuntimeException
import java.time.LocalDate

class DateRecordListIndexOutOfBoundsException(date: Long, index: Int)
    : RuntimeException("날짜 ${LocalDate.ofEpochDay(date)}, 인덱스 ${index}가 list 범위를 초과하였습니다.")