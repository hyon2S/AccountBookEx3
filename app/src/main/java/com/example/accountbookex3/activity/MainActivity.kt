package com.example.accountbookex3.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.accountbookex3.R
import com.example.accountbookex3.fragment.ButtonFragment
import com.example.accountbookex3.fragment.RecyclerViewFragment
import com.example.accountbookex3.util.ActivityStarter

/*
* startActivity(intent) 관련 기능을 하는 구성요소들:
* private val insertActivityStarter
* private fun attachFragment()
* */
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivityLog"
    private val insertActivityStarter =
            ActivityStarter(
                    this,
                    InsertFormActivity::class.java
            )

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        attachFragment()

//        startInsertActivity()
        // 리포지토리 테스트
/*
        val realm = Realm.getDefaultInstance()
        val repository = Repository(realm)
        val test = RepositoryTest(repository)
        Log.d(TAG, "Repository test 생성")
        test.test()
*/

        // realm db 초기화 코드.
/*
        val dbViewModel = ViewModelProvider(this).get(DbViewModel::class.java)
        dbViewModel.deleteAll()
*/
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
        super.onDestroy()
    }

    private fun attachFragment() {
        Log.d(TAG, "attachFragment()")
        val rvFragment: RecyclerViewFragment =
                supportFragmentManager.findFragmentById(R.id.csl_recycler_view) as RecyclerViewFragment? ?: RecyclerViewFragment()
        val btnFragment: ButtonFragment =
                supportFragmentManager.findFragmentById(R.id.csl_buttons) as ButtonFragment? ?: ButtonFragment()

        supportFragmentManager.beginTransaction()
                .replace(R.id.csl_recycler_view, rvFragment)
                .replace(R.id.csl_buttons, btnFragment)
                .commit()
        Log.d(TAG, "프래그먼트 붙임")
    }

    /*
    * 버튼 눌러서 ButtonFragment로부터 호출될 예정
    * */
    fun startInsertActivity() {
        Log.d(TAG, "startInsertActivity()")
        insertActivityStarter.startActivity()
        // DbViewModel 테스트
/*
        val dbViewModel = ViewModelProvider(this).get(DbViewModel::class.java)
        Log.d(TAG, "dbViewModel 객체 생성")
        val test = DbViewModelTest(dbViewModel)
        Log.d(TAG, "DbViewModel test 생성")
        test.test()
*/
    }
}