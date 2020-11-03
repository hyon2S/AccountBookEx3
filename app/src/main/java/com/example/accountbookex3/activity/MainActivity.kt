package com.example.accountbookex3.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.fragment.MainButtonFragment
import com.example.accountbookex3.fragment.RecyclerViewFragment
import com.example.accountbookex3.util.DeletionAlertDialogFragment
import com.example.accountbookex3.util.InsertFormActivityStarter
import com.example.accountbookex3.util.UpdateActivityStarter
import com.example.accountbookex3.viewmodel.DbViewModel

/*
* startActivity(intent) 관련 기능을 하는 구성요소들:
* private val insertActivityStarter
* private fun attachFragment()
* */
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivityLog"

    private val DELETE_FRAG_TAG = "delete_fragment"

    private val insertActivityStarter = InsertFormActivityStarter(this)
    private val updateActivityStarter = UpdateActivityStarter(this)

    private val dbViewModel by lazy { ViewModelProvider(this).get(DbViewModel::class.java) }

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

/*
    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
        super.onDestroy()
    }
*/

    private fun attachFragment() {
        Log.d(TAG, "attachFragment()")
        val rvFragment: RecyclerViewFragment =
                supportFragmentManager.findFragmentById(R.id.csl_recycler_view) as RecyclerViewFragment? ?: RecyclerViewFragment()
        val btnFragment: MainButtonFragment =
                supportFragmentManager.findFragmentById(R.id.csl_buttons) as MainButtonFragment? ?: MainButtonFragment()

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

        // 버튼 눌러서 테스트 용
        // startUpdateActivity("2020-10-26", 1)

        // DbViewModel 테스트
/*
        val dbViewModel = ViewModelProvider(this).get(DbViewModel::class.java)
        Log.d(TAG, "dbViewModel 객체 생성")
        val test = DbViewModelTest(dbViewModel)
        Log.d(TAG, "DbViewModel test 생성")
        test.test()
*/
    }

    fun startUpdateActivity(date: String, index: Int) {
        Log.d(TAG, "startUpdateActivity()")
        updateActivityStarter.startActivity(date, index)
    }

    // AlertDialog 띄워서 yes면 지움
    fun startDeletionAlertDialog(date: String, index: Int) {
        Log.d(TAG, "startUpdateActivity()")
        val positiveCallback = DialogInterface.OnClickListener{ dialog, which ->
            Log.d(TAG, "positive click")
            dbViewModel.delete(date, index)
            Toast.makeText(this, R.string.delete_done_message, Toast.LENGTH_SHORT).show()
        }
        val negativeCallback = DialogInterface.OnClickListener{ dialog, which ->
            Log.d(TAG, "negative click")
            // 아무것도 안함
        }
        val dialog = DeletionAlertDialogFragment(positiveCallback, negativeCallback)
        dialog.retainInstance = true
        dialog.show(supportFragmentManager, DELETE_FRAG_TAG)
    }
}