package com.example.accountbookex3.dragandswipe

/*
* https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf
* 의 예제 참고
* */
interface ItemTouchHelperAdapter {
    // fromPosition의 아이템을 toPosition 위치로 이동시킴
    fun onItemMove(fromPosition: Int, toPosition: Int)
    // position위치의 아이템을 삭제하려고 체크해둠.
    fun onItemDeleteCheck(position: Int)
}