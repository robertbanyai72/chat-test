package hu.robert.banyai.lmitest.presentation.common

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import hu.robert.banyai.lmitest.domain.resource.ResourceHelper
import lmitest.banyai.robert.com.logmeintest.R

class SpaceItemDecoration(private val resourceHelper: ResourceHelper) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = resourceHelper.dimensionInPixes(R.dimen.adapter_item_bottom_margin)
        outRect.top = resourceHelper.dimensionInPixes(R.dimen.adapter_item_top_margin)
        outRect.right = resourceHelper.dimensionInPixes(R.dimen.adapter_item_right_margin)
        outRect.left = resourceHelper.dimensionInPixes(R.dimen.adapter_item_left_margin)
    }
}