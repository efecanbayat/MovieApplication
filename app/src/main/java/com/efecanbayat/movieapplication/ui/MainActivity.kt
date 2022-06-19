package com.efecanbayat.movieapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.efecanbayat.movieapplication.databinding.ActivityMainBinding
import com.efecanbayat.movieapplication.utils.gone
import com.efecanbayat.movieapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun isVisibleBackButton(isVisibleButton: Boolean) {
        if (isVisibleButton) binding.btnBack.show()
        else binding.btnBack.gone()
    }

    fun backButtonClick(action: () -> Unit) {
        binding.btnBack.setOnClickListener {
            action.invoke()
        }
    }

    fun isVisibleToolbar(isVisibleToolbar: Boolean) {
        if (isVisibleToolbar) binding.toolbar.show()
        else binding.toolbar.gone()
    }
}