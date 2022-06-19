package com.efecanbayat.movieapplication.ui.feature.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.efecanbayat.movieapplication.R
import com.efecanbayat.movieapplication.databinding.FragmentHomeBinding
import com.efecanbayat.movieapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnHomeItemClickListener{
    override val layoutId: Int = R.layout.fragment_home

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.onItemClickListener = this
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchMovieAndPerson(newText)
                return true
            }

        })

        lifecycleScope.launchWhenResumed {
            viewModel.uiState.collect {
                if (it.isLoading) showProgress() else hideProgress()
                if (it.errorMessage?.isNotEmpty() == true) showDialog(it.errorMessage)
            }
        }
    }

    override fun onMovieClick(movieId: Int?) {
        movieId?.let {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movieId))
        }
    }

    override fun onSearchedPersonClick(personId: Int?) {
        Log.v("Efecan","SearchedPersonClick")
    }
}

interface OnHomeItemClickListener {
    fun onMovieClick(movieId: Int?)
    fun onSearchedPersonClick(personId: Int?)
}