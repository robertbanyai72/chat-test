package hu.robert.banyai.lmitest.presentation.common.extension

import android.view.View

fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}