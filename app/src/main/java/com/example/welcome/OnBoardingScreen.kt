package com.example.welcome

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.welcome.OnBoarding.OnboardingAdapter
import com.example.welcome.OnBoarding.OnboardingPageTransformer
import com.example.welcome.databinding.FragmentLoginBinding
import com.example.welcome.databinding.FragmentVerificationNewBinding
import com.example.welcome.databinding.OnBoardingScreenBinding
import com.example.welcome.databinding.SplashBinding
import com.google.android.material.snackbar.Snackbar

class OnBoardingScreen : Fragment() {

    private var _binding: OnBoardingScreenBinding? = null

    private val binding get() = _binding!!

    private lateinit var onboardingAdapter: OnboardingAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = OnBoardingScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener { onClick() }

        makeStatusbarTransparent()

        onboardingAdapter = OnboardingAdapter(requireContext())
        binding.viewpage.adapter = onboardingAdapter
        binding.viewpage.setPageTransformer(false, OnboardingPageTransformer())

    }

    private fun makeStatusbarTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {
            requireActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = requireActivity().window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun onClick() {
        findNavController().navigate(R.id.action_onBoardingScreen_to_loginFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}