package com.example.accountbookex3.model

import com.example.accountbookex3.AccountBookApplication
import com.example.accountbookex3.R
import com.example.accountbookex3.data.DateRecord
import com.example.accountbookex3.data.FormedRecord
import com.example.accountbookex3.data.Record
import com.example.accountbookex3.exception.DateRecordNotFoundException
import com.example.accountbookex3.exception.RecordFormException
import io.realm.Realm
import io.realm.RealmResults

class Repository(val realm: Realm) {
    private val dateRecordDao = DateRecordDao(realm)
    private val recordDao = RecordDao(realm)

    fun insert(formedRecord: FormedRecord) {
        val date: String = try {
            formedRecord.getDate()
        } catch (e: Exception) {
            throw RecordFormException(AccountBookApplication.applicationContext().resources.getString(R.string.date_is))
        }
        val isIncome: Boolean = try {
            formedRecord.getIsIncome()
        } catch (e: Exception) {
            throw RecordFormException(AccountBookApplication.applicationContext().resources.getString(R.string.income_outcome_is))
        }
        val amount: Int = try {
            formedRecord.getAmount().toInt()
        } catch (e: Exception) {
            throw RecordFormException(AccountBookApplication.applicationContext().resources.getString(
                    R.string.amount_is))
        }

        val dateRecord: DateRecord = getDateRecord(date) // 날짜 정보를 얻어옴.
        val record = recordDao.insert(isIncome, amount)
        dateRecord.add(record)
    }

    fun selectAll(): RealmResults<DateRecord> =
            dateRecordDao.selectAll()

    fun select(date: String, index: Int): Record {
        val dateRecord: DateRecord = dateRecordDao.select(date) ?: throw DateRecordNotFoundException(date)
        return dateRecord.get(index)
    }

    fun delete(date: String, index: Int) {
        // DateRecord의 list에서 빼낸 다음
        val dateRecord: DateRecord = dateRecordDao.select(date) ?: throw DateRecordNotFoundException(date)
        val record = dateRecord.removeAt(index)
        // realm에서도 지우기
        recordDao.delete(record)
        // dateRecord에 record가 하나도 안 남아있으면 없애버리기.
        dateRecordDao.deleteIfEmpty(dateRecord)
    }

    fun moveRecord(date: String, fromIndex: Int, toIndex: Int) {
        val dateRecord: DateRecord = dateRecordDao.select(date) ?: throw DateRecordNotFoundException(date)
        // fromIndex에서 빼내서
        val record = dateRecord.removeAt(fromIndex)
        // toIndex자리에 넣는다.
        dateRecord.addAt(toIndex, record)
    }

    private fun getDateRecord(date: String): DateRecord {
        return  dateRecordDao.select(date) ?: dateRecordDao.insert(date)
    }
}
