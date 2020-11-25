package com.example.accountbookex3.database

import com.example.accountbookex3.data.Record
import io.realm.Realm
import io.realm.kotlin.where

class RecordDao(val realm: Realm) {
    fun insert(isIncome: Boolean, amount: Int, memo: String): Record {
        val id: Long = getId()
        // 적절한 id를 부여해서 새 레코드를 만듦.
        val newRecord: Record = realm.createObject(Record::class.java, id)

        newRecord.apply {
            this.isIncome = isIncome
            this.amount = amount
            this.memo = memo
        }

        return newRecord
    }

    // realm에서 record를 삭제함.
    fun delete(record: Record) {
        record.deleteFromRealm()
    }

    /*
    * https://stackoverflow.com/questions/40174920/how-to-set-primary-key-auto-increment-in-realm-android
    * 의 예시를 적당히 참고하여 만듦
    * */
    private fun getId(): Long { // 0부터 시작
        val number = realm.where<Record>().max("id")
        return if (number != null) (number.toLong() + 1) else 0
    }
}