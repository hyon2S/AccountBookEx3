package com.example.accountbookex3.exception

import java.lang.RuntimeException

class DateRecordListIndexOutOfBoundsException(date: String, index: Int)
    : RuntimeException("날짜 ${date}, 인덱스 ${index}가 list 범위를 초과하였습니다.")