package com.efecanbayat.movieapplication.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.efecanbayat.movieapplication.R

class CustomProgressBar(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_progress_bar)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}