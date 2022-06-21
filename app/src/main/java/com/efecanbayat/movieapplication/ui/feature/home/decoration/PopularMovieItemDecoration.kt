package com.efecanbayat.movieapplication.ui.feature.home.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PopularMovieItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (parent.getChildLayoutPosition(view)) {
            0 -> {
                outRect.left = spacing * 2
                outRect.right = spacing * 2
                outRect.bottom = spacing
                outRect.top = 0
            }
            parent.adapter?.itemCount?.minus(1) -> {
                outRect.left = spacing * 2
                outRect.right = spacing * 2
                outRect.bottom = 0
                outRect.top = spacing
            }
            else -> {
                outRect.left = spacing * 2
                outRect.right = spacing * 2
                outRect.bottom = spacing
                outRect.top = spacing
            }
        }
    }
}