package com.example.accountbookex3

import android.app.Application
import android.content.Context
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmMigration

class AccountBookApplication: Application() {
    private val TAG = "ApplicationLog"

    /*
    * companion object 처리 부분은 여기 참고해서 만듦.
    * https://stackoverflow.com/questions/54075649/access-application-context-in-companion-object-in-kotlin
    * */
    init {
        instance = this
    }
    companion object {
        private var instance: AccountBookApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = AccountBookApplication.applicationContext()
        Realm.init(this)
        Log.d(TAG, "realm init")

        val config = RealmConfiguration.Builder()
                .name("accountrealm.realm")
                .schemaVersion(0)
                .migration(RealmMigration { realm, oldVersion, newVersion ->
                    // val schema = realm.schema
                    // if (oldVersion == ~~~)
                })
                .build()

        Realm.setDefaultConfiguration(config)

        config.apply {
            Log.d(TAG, "${path}, ${schemaVersion}")
        }
    }
}