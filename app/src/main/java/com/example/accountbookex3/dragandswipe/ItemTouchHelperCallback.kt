package com.example.accountbookex3.dragandswipe

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/*
* https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf
* 의 예제 참고
* */
class ItemTouchHelperCallback(val adapter: ItemTouchHelperAdapter): ItemTouchHelper.Callback() {
    private val TAG = "ItemTouchHelperCallbackLog"

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        Log.d(TAG, "onMove(${viewHolder.adapterPosition} -> ${target.adapterPosition})")
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemDeleteCheck(viewHolder.adapterPosition)
    }

    // 지금은 편의상 true로 둠. ItemTouchHelper.startDrag() 설정 한 다음에 꼭 false로 바꾸기.
    override fun isLongPressDragEnabled(): Boolean = true
    override fun isItemViewSwipeEnabled(): Boolean = true
}