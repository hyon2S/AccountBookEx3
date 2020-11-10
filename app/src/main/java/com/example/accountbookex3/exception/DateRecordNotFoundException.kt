package com.example.accountbookex3.exception

import java.lang.RuntimeException
import java.time.LocalDate

class DateRecordNotFoundException(date: Long):
        RuntimeException("날짜 ${LocalDate.ofEpochDay(date)}의 DateRecord가 존재하지 않습니다.")