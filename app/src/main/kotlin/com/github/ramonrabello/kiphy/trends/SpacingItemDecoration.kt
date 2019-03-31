package com.github.ramonrabello.kiphy.trends

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Spacing item decoration for recycler view.
 */
class SpacingItemDecoration(private val itemOffset:Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.apply {
            bottom = itemOffset
            top = itemOffset
            left = itemOffset
            right = itemOffset
        }
    }
}