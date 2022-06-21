package com.efecanbayat.movieapplication.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.efecanbayat.movieapplication.R
import com.efecanbayat.movieapplication.ui.MainActivity
import com.efecanbayat.movieapplication.ui.dialog.CustomProgressBar

abstract class BaseFragment<binding : ViewDataBinding> : Fragment() {
    abstract val layoutId: Int

    private var progressBar: CustomProgressBar? = null
    lateinit var binding: binding

    private var pageTitle: String = "Movies"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (::binding.isInitialized) {
            return binding.root
        }
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProgressBar()
        pageConfigs()
    }

    private fun pageConfigs() {
        with(activity as MainActivity) {
            when (layoutId) {
                R.layout.fragment_home -> {
                    isVisibleToolbar(true)
                    setPageTitle("Movies")
                    setTitleText(pageTitle)
                    isVisibleBackButton(false)
                }
                R.layout.fragment_movie_detail -> {
                    isVisibleToolbar(true)
                    setTitleText(pageTitle)
                    isVisibleBackButton(true)
                    backButtonClick {
                        findNavController().popBackStack()
                    }
                }
                R.layout.fragment_person_detail -> {
                    isVisibleToolbar(true)
                    setTitleText(pageTitle)
                    isVisibleBackButton(true)
                    backButtonClick {
                        findNavController().popBackStack()
                    }
                }
                R.layout.fragment_splash -> {
                    isVisibleToolbar(false)
                }
            }
        }
    }

    fun setPageTitle(title: String) {
        pageTitle = title
    }

    fun showProgress() {
        progressBar?.show()
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    fun hideProgress() {
        progressBar?.dismiss()
        activity?.window?.clearFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    private fun initProgressBar() {
        context?.let {
            progressBar = CustomProgressBar(it)
        }
    }

    fun showDialog(message: String, action: () -> Unit = {}) {
        AlertDialog.Builder(context).apply {
            setTitle("Uyarı")
            setMessage(message)
            setCancelable(false)
            setPositiveButton("Tamam") { _, _ -> action.invoke() }
        }.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        progressBar = null
    }
}