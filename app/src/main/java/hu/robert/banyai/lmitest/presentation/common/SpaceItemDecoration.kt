package hu.robert.banyai.lmitest.presentation.common

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpaceItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = 5
        outRect.top = 5
        outRect.right = 20
        outRect.left = 20
    }
}