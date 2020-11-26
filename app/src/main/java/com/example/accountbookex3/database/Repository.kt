package com.example.accountbookex3.database

import com.example.accountbookex3.AccountBookApplication
import com.example.accountbookex3.R
import com.example.accountbookex3.data.DateRecord
import com.example.accountbookex3.data.InputFormData
import com.example.accountbookex3.data.Record
import com.example.accountbookex3.data.RecordInfo
import com.example.accountbookex3.exception.DateRecordNotFoundException
import com.example.accountbookex3.exception.RecordFormException
import io.realm.Realm
import io.realm.RealmResults
import java.time.LocalDate

class Repository(val realm: Realm) {
    private val dateRecordDao = DateRecordDao(realm)
    private val recordDao = RecordDao(realm)

    fun insert(inputFormData: InputFormData, index: Int = 0) {
        val date: Long = try {
            dateToLong(inputFormData.getDate())
        } catch (e: Exception) {
            throw RecordFormException(AccountBookApplication.applicationContext().resources.getString(R.string.date_is))
        }
        val isIncome: Boolean = try {
            inputFormData.getIsIncome()
        } catch (e: Exception) {
            throw RecordFormException(AccountBookApplication.applicationContext().resources.getString(R.string.income_outcome_is))
        }
        val amount: Int = try {
            inputFormData.getAmount().toInt()
        } catch (e: Exception) {
            throw RecordFormException(AccountBookApplication.applicationContext().resources.getString(
                    R.string.amount_is))
        }
        val memo: String = inputFormData.getMemo()

        val dateRecord: DateRecord = getDateRecord(date) // 날짜 정보를 얻어옴.
        val record = recordDao.insert(isIncome, amount, memo)
        dateRecord.add(index, record)
    }

    fun update(recordInfo: RecordInfo, inputFormData: InputFormData) {
        delete(recordInfo)
        if (recordInfo.date == inputFormData.getDate())
        // 기존 인덱스 자리에 그대로 대체
            insert(inputFormData, recordInfo.index)
        else
            insert(inputFormData)
    }

    fun selectBetween(fromDate: LocalDate, toDate: LocalDate): RealmResults<DateRecord> =
            dateRecordDao.selectBetween(dateToLong(fromDate), dateToLong(toDate))

    fun selectAll(): RealmResults<DateRecord> =
            dateRecordDao.selectAll()

    fun select(recordInfo: RecordInfo): Record {
        val dateRecord: DateRecord = getDateRecordOrException(dateToLong(recordInfo.date))
        return dateRecord.get(recordInfo.index)
    }

    fun delete(recordInfo: RecordInfo) {
        // DateRecord의 list에서 빼낸 다음
        val dateRecord: DateRecord = getDateRecordOrException(dateToLong(recordInfo.date))
        val record = dateRecord.removeAt(recordInfo.index)
        // realm에서도 지우기
        recordDao.delete(record)
        // dateRecord에 record가 하나도 안 남아있으면 없애버리기.
        dateRecordDao.deleteIfEmpty(dateRecord)
    }

    fun moveRecord(date: LocalDate, fromIndex: Int, toIndex: Int) {
        val dateRecord: DateRecord = getDateRecordOrException(dateToLong(date))
        // fromIndex에서 빼내서
        val record = dateRecord.removeAt(fromIndex)
        // toIndex자리에 넣는다.
        dateRecord.add(toIndex, record)
    }

    // 날짜로부터 DateRecord를 얻어오는데, 없으면 만들어서 얻어옴.
    private fun getDateRecord(date: Long): DateRecord {
        return  dateRecordDao.select(date) ?: dateRecordDao.insert(date)
    }

    // 날짜로부터 DateRecord를 얻어오는데, 없으면 예외를 발생시킴.
    private fun getDateRecordOrException(date: Long): DateRecord =
            dateRecordDao.select(date) ?: throw DateRecordNotFoundException(date)

    private fun dateToLong(date: LocalDate): Long =
            date.toEpochDay()

    // fromDate ~ toDate 기간 동안의 총수입/지출을 계산
    fun getTotalIncomeOutcomeBetween(fromDate: LocalDate, toDate: LocalDate, isIncome: Boolean): Int {
        var total: Int = 0
        for (date in dateToLong(fromDate)..dateToLong(toDate)) {
            val dateRecord: DateRecord = dateRecordDao.select(date) ?: continue
            val list = dateRecord.list
            for (record in list) {
                if (record.isIncome == isIncome)
                    total += record.amount
            }
        }
        return total
    }
}
