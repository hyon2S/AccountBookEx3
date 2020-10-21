package com.example.accountbookex3.util

import android.app.Activity
import android.content.Intent
import com.example.accountbookex3.activity.InsertFormActivity

class InsertFormActivityStarter(private val currentActivity: Activity) {
    fun startActivity() {
        val insertIntent = Intent(currentActivity, InsertFormActivity::class.java)
        currentActivity.startActivity(insertIntent)
    }
    // fun startActivityForResult()도 만들어야되나?
}