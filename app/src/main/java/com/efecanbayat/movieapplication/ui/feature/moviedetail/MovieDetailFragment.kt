package com.efecanbayat.movieapplication.ui.feature.moviedetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.efecanbayat.movieapplication.R
import com.efecanbayat.movieapplication.databinding.FragmentMovieDetailBinding
import com.efecanbayat.movieapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>(), OnCastItemClickListener {
    override val layoutId: Int = R.layout.fragment_movie_detail

    private val viewModel by viewModels<MovieDetailViewModel>()
    private val args: MovieDetailFragmentArgs by navArgs()

    private var videoKey: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setPageTitle(args.title ?: getString(R.string.movie_title))
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovie(args.id)
        binding.onItemClickListener = this

        binding.videoPlayer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoKey"))
            intent.putExtra("VIDEO_ID", videoKey)
            startActivity(intent)
        }

        lifecycleScope.launchWhenResumed {
            viewModel.uiState.collectLatest {
                if (it.isLoading) showProgress() else hideProgress()
                if (it.errorMessage?.isNotEmpty() == true) showDialog(it.errorMessage)
                if (it.movie != null) {
                    binding.movie = it.movie
                    binding.executePendingBindings()
                }
                if (it.movieCredits?.castItem?.isNotEmpty() == true) {
                    binding.castData = it.movieCredits
                    binding.executePendingBindings()
                }
                if (it.movieVideo != null) {
                    videoKey = it.movieVideo
                    binding.videoPlayer.visibility = View.VISIBLE
                } else {
                    binding.videoPlayer.visibility = View.GONE
                }
            }
        }
    }

    override fun onCastItemClick(personId: Int?, name: String?) {
        personId?.let {
            findNavController().navigate(
                MovieDetailFragmentDirections.actionMovieDetailFragmentToPersonDetailFragment(
                    personId,
                    name
                )
            )
        }
    }
}

interface OnCastItemClickListener {
    fun onCastItemClick(personId: Int?, name: String?)
}