package com.efecanbayat.movieapplication.utils

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun doubleToFloatWithDiv(value: Double?, part: Int = 1): Float? {
    return value?.toFloat()?.div(part)
}