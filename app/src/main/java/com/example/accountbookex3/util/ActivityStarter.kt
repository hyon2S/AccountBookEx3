package com.example.accountbookex3.util

import android.app.Activity
import android.content.Intent

class ActivityStarter<T: Activity>(private val currentActivity: Activity, private val newActivityClass: Class<T>) {
    fun startActivity() {
        val insertIntent = Intent(currentActivity, newActivityClass)
        currentActivity.startActivity(insertIntent)
    }
    // fun startActivityForResult()도 만들어야되나?
}