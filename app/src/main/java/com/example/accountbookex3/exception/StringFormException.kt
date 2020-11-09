package com.example.accountbookex3.exception

import java.lang.RuntimeException

class StringFormException(field: String):
        RuntimeException("텍스트를 ${field} 형식으로 변환할 수 없습니다.")