package com.example.welcome

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.welcome.databinding.SplashLoadingBinding


class Splashloading : Fragment() {

    private var _binding: SplashLoadingBinding? = null

    private val binding get() = _binding!!

    private val navigationDelay = 2000L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SplashLoadingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            navigateToNextFragment()
        }, navigationDelay)
    }

    private fun navigateToNextFragment() {
        findNavController().navigate(R.id.action_splashloading_to_onBoardingScreen)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}