package com.ironflowers.fbt2.core.ui.common.extensions

import android.view.View
import android.view.View.*

fun View.setVisibleOrGone(show: Boolean) {
    visibility = if (show) VISIBLE else GONE
}

fun View.setVisible(show: Boolean) {
    visibility = if (show) VISIBLE else INVISIBLE
}
