package com.efecanbayat.movieapplication.ui.feature.persondetail.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CreditsItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (parent.getChildLayoutPosition(view)) {
            0 -> {
                outRect.left = 0
                outRect.right = spacing
                outRect.bottom = spacing
                outRect.top = spacing
            }
            parent.adapter?.itemCount?.minus(1) -> {
                outRect.left = spacing
                outRect.right = 0
                outRect.bottom = spacing
                outRect.top = spacing
            }
            else -> {
                outRect.left = spacing
                outRect.right = spacing
                outRect.bottom = spacing
                outRect.top = spacing
            }
        }
    }
}
