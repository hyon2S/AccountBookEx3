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

    fun add(index: Int = 0, record: Record) {
        // 같은 날짜 안에서는 최신 데이터를 앞으로 넣기
        try {
            list.add(index, record)
            // realm도 LinkedList 이런거 지원하면 좋을텐데.
        } catch (e: IndexOutOfBoundsException) { // if index < 0 || index > size()
            throw DateRecordListIndexOutOfBoundsException(date, index)
        }
    }

    fun get(index: Int): Record {
        try {
            return list[index]!! // !!하는게 옳은 선택일까...?
        } catch (e: IndexOutOfBoundsException) {
            throw DateRecordListIndexOutOfBoundsException(date, index)
        }
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