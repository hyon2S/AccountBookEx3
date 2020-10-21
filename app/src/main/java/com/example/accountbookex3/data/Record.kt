package com.example.accountbookex3.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Record(
    @PrimaryKey var id: Long = 0,
    var isIncome: Boolean = false,
    var amount: Int = 0
): RealmObject() {
    override fun toString() =
        "id: ${id}, ${if (isIncome) "수입" else "지출"} ${amount}원"
}