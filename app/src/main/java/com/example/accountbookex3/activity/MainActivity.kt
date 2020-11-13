package com.example.accountbookex3.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.accountbookex3.R
import com.example.accountbookex3.fragment.MainButtonFragment
import com.example.accountbookex3.fragment.RecyclerViewFragment
import com.example.accountbookex3.edit.DeleteAlertDialogFragment
import com.example.accountbookex3.edit.DeleteDialogHelper
import com.example.accountbookex3.datepicker.DatePickerFragment
import com.example.accountbookex3.datepicker.DatePickerHelper
import com.example.accountbookex3.fragment.DateFragment
import com.example.accountbookex3.fragment.InsertFragment
import com.example.accountbookex3.viewmodel.*
import java.time.LocalDate

/*
* startActivity(intent) 관련 기능을 하는 구성요소들:
* private val insertActivityStarter
* private fun attachFragment()
* */
class MainActivity : AppCompatActivity(), DeleteDialogHelper, DatePickerHelper {
    private val TAG = "MainActivityLog"

    private val DELETE_FRAG_TAG = "delete_fragment"
    private val DATE_PICKER_FRAG_TAG = "date_picker"
    private val INSERT_FRAGMENT_TAG = "insert_fragment"

    /*
    * MainActivity에 붙은 다른 프래그먼트에서 MainActivity에서 만든 뷰모델을 그냥 가져다가 쓰기 때문에,
    * lazy 초기화 말고 lateinit를 사용함.
    * */
    private lateinit var dbViewModel: DbViewModel
    private lateinit var insertViewModel: InsertViewModel

    private fun initViewModel() {
        dbViewModel = ViewModelProvider(this).get(DbViewModel::class.java)
        insertViewModel = ViewModelProvider(this, InsertViewModelFactory(dbViewModel))
            .get(InsertViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
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
        val dateFragment: DateFragment =
                supportFragmentManager.findFragmentById(R.id.csl_date) as DateFragment? ?: DateFragment()

        supportFragmentManager.beginTransaction()
                .replace(R.id.csl_recycler_view, rvFragment)
                .replace(R.id.csl_buttons, btnFragment)
                .replace(R.id.csl_date, dateFragment)
                .commit()
        Log.d(TAG, "프래그먼트 붙임")
    }

    /*
    * 버튼 눌러서 ButtonFragment로부터 호출될 예정
    * */
    fun startInsertFragment() {
        Log.d(TAG, "startInsertFragment()")
        insertViewModel.initFormedRecord() // InsertFragment를 두 번째 호출할 때 부터는 이전의 정보가 남을 수 있기 때문에 초기화 시켜줌
        val insertFragment: InsertFragment? = supportFragmentManager.findFragmentByTag(INSERT_FRAGMENT_TAG) as InsertFragment?
        if (insertFragment == null)
            InsertFragment.newInstance().show(supportFragmentManager.beginTransaction(), INSERT_FRAGMENT_TAG)
    }

    fun startUpdateActivity(date: LocalDate, index: Int) {
        Log.d(TAG, "startUpdateActivity()")
        val updateIntent = Intent(this, UpdateActivity::class.java)
        updateIntent.apply {
            putExtra("date", date.toString())
            putExtra("index", index)
        }
        startActivity(updateIntent)
    }

    // AlertDialog 띄워서 yes면 지움
    fun startDeleteAlertDialog(date: LocalDate, index: Int) {
        Log.d(TAG, "startDeleteAlertDialog()")
        DeleteAlertDialogFragment.newInstance(date, index)
                .show(supportFragmentManager, DELETE_FRAG_TAG)
    }

    // override DeleteDialogHelper
    override fun delete(date: LocalDate, index: Int) {
        Log.d(TAG, "delete(${date}, ${index})")
        dbViewModel.delete(date, index)
        Toast.makeText(this, R.string.delete_done_message, Toast.LENGTH_SHORT).show()
    }

    // DatePickerHelper 구현 항목들
    // InsertActivity와 완전 똑같음.
    override val datePickerViewModel by lazy { ViewModelProvider(this).get(DatePickerViewModel::class.java) }

    override fun chooseDate(oldDate: LocalDate, callback: (LocalDate) -> Unit) {
        datePickerViewModel.callback = callback
        DatePickerFragment.newInstance(oldDate).show(supportFragmentManager, DATE_PICKER_FRAG_TAG)
    }
}