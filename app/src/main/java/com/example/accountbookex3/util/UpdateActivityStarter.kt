package com.example.accountbookex3.util

import android.app.Activity
import android.content.Intent
import com.example.accountbookex3.activity.UpdateActivity

class UpdateActivityStarter(private val currentActivity: Activity) {
    private val TAG = "UpdateActivityStarterLog"

    fun startActivity(date: String, index: Int) {
        val updateIntent = Intent(currentActivity, UpdateActivity::class.java)
        updateIntent.apply {
            putExtra("date", date)
            putExtra("index", index)
        }
        currentActivity.startActivity(updateIntent)
    }
}