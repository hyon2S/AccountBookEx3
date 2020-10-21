package com.example.accountbookex3.exception

import java.lang.RuntimeException

class RecordFormException(field: String):
    RuntimeException("${field} 올바르게 입력되었는지 확인해주세요.")