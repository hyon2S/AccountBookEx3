package com.example.accountbookex3.exception

import java.lang.RuntimeException

class DateRecordNotFoundException(date: String):
        RuntimeException("날짜 ${date}의 DateRecord가 존재하지 않습니다.")