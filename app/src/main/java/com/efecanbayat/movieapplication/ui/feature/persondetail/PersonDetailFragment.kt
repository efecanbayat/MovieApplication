package com.efecanbayat.movieapplication.ui.feature.persondetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.efecanbayat.movieapplication.R
import com.efecanbayat.movieapplication.databinding.FragmentPersonDetailBinding
import com.efecanbayat.movieapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PersonDetailFragment : BaseFragment<FragmentPersonDetailBinding>() {
    override val layoutId: Int = R.layout.fragment_person_detail

    private val viewModel by viewModels<PersonDetailViewModel>()
    private val args: PersonDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setPageTitle(args.name ?: getString(R.string.person_name))
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPerson(args.id)

        lifecycleScope.launchWhenResumed {
            viewModel.uiState.collectLatest {
                if (it.isLoading) showProgress() else hideProgress()
                if (it.errorMessage?.isNotEmpty() == true) showDialog(it.errorMessage)
                if (it.person != null) {
                    binding.person = it.person
                    binding.executePendingBindings()
                }
                if (it.personCredits?.creditsItem?.isNotEmpty() == true) {
                    binding.creditsData = it.personCredits
                    binding.executePendingBindings()
                }
            }
        }
    }
}