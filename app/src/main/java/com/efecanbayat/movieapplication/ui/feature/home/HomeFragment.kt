package com.efecanbayat.movieapplication.ui.feature.home

import com.efecanbayat.movieapplication.R
import com.efecanbayat.movieapplication.databinding.FragmentHomeBinding
import com.efecanbayat.movieapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutId: Int = R.layout.fragment_home
}