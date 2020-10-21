package com.example.accountbookex3.data

import com.example.accountbookex3.exception.DateRecordListIndexOutOfBoundsException
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.lang.IndexOutOfBoundsException
import java.lang.StringBuilder

open class DateRecord(
    @PrimaryKey var date: String = "",
    var list: RealmList<Record> = RealmList()
): RealmObject() {
    override fun toString(): String {
        val sb = StringBuilder()

        sb.append("${date}\n")
        for (record in list)
            sb.append(record.toString()).append("\n")

        return sb.toString()
    }

    fun addAt(index: Int, record: Record) {
        try {
            list.add(index, record)
        } catch (e: IndexOutOfBoundsException) { // if index < 0 || index > size()
            throw DateRecordListIndexOutOfBoundsException(date, index)
        }
    }

    fun add(record: Record) {
        // 같은 날짜 안에서는 최신 데이터를 앞으로 넣기
        addAt(0, record)
        // realm도 LinkedList 이런거 지원하면 좋을텐데.
    }

    fun removeAt(index: Int): Record {
        try {
            return list.removeAt(index)
        } catch (e: IndexOutOfBoundsException) {
            throw DateRecordListIndexOutOfBoundsException(date, index)
        }
    }

    fun isEmpty(): Boolean = list.isEmpty()
}