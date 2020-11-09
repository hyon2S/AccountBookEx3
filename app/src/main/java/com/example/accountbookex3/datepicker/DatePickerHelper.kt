package com.example.accountbookex3.datepicker

import com.example.accountbookex3.viewmodel.DatePickerViewModel

/*
* DatePickerDialog를 이용해서 날짜를 선택하는 것을 도와줌.
* datePickerViewModel은 callback을 보관하는 역할을 함.
* 일반 변수에 callback을 보관하면 화면을 회전시키면 callback이 초기화되어버려 ViewModel을 사용함.
* chooseDate 내부에서 DatePickerFragment를 호출하도록 override해야됨.
* */
interface DatePickerHelper {
    val datePickerViewModel: DatePickerViewModel

    /*
    * 내부에서 DatePickerFragment를 호출해서 날짜를 선택하게 함.
    * 먼저 callback을 datePickerViewModel에 저장해놓고 다른 작업 수행.
    * DatePickerFragment의 onDateSet에서 callback을 수행함.
    * callback에는 ViewModel의 날짜 저장 변수를 set하는 내용이 들어가게 됨.
    *
    * 문제: chooseDate()의 구현 내용이 모든 액티비티에서 완전 똑같음.
    override fun chooseDate(oldDate: String, callback: (String) -> Unit) {
        datePickerViewModel.callback = callback
        DatePickerFragment.newInstance(oldDate).show(supportFragmentManager, DATE_PICKER_FRAG_TAG)
    }
    * 반복을 줄일 수 있는 방법을 생각해봅시다.
    * */
    fun chooseDate(oldDate: String, callback: (String) -> Unit)
}