package com.efecanbayat.movieapplication.ui.feature.splash

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.efecanbayat.movieapplication.R
import com.efecanbayat.movieapplication.databinding.FragmentSplashBinding
import com.efecanbayat.movieapplication.ui.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override val layoutId: Int = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.splashAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                Log.v("Animation", "Started")
            }

            override fun onAnimationEnd(p0: Animator?) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }

            override fun onAnimationCancel(p0: Animator?) {
                Log.v("Animation", "Canceled")
            }

            override fun onAnimationRepeat(p0: Animator?) {
                Log.v("Animation", "Repeated")
            }
        })
    }
}