package com.example.accountbookex3.database

import com.example.accountbookex3.data.DateRecord
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import io.realm.kotlin.where

class DateRecordDao(val realm: Realm) {
    fun select(date: Long): DateRecord? =
            realm.where<DateRecord>().equalTo("date", date).findFirst()

    fun insert(date: Long): DateRecord =
            realm.createObject(DateRecord::class.java, date)

    fun selectBetween(fromDate: Long, toDate: Long): RealmResults<DateRecord> =
            realm.where<DateRecord>().between("date", fromDate, toDate).sort("date", Sort.DESCENDING).findAll()

    fun selectAll(): RealmResults<DateRecord> =
            realm.where<DateRecord>().sort("date", Sort.DESCENDING).findAll()

    // dateRecord의 list에 아무것도 없으면 realm에서 dateRecord를 삭제함.
    fun deleteIfEmpty(dateRecord: DateRecord) {
        if (dateRecord.isEmpty())
            dateRecord.deleteFromRealm()
    }
}