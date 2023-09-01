package com.example.welcome

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.welcome.databinding.FragmentLoginBinding
import com.example.welcome.databinding.FragmentVerificationNewBinding
import com.example.welcome.databinding.SplashBinding
import com.google.android.material.snackbar.Snackbar

class Splash : Fragment() {

    private var _binding: SplashBinding? = null

    private val binding get() = _binding!!

    private lateinit var imgSplash: ImageButton
    private lateinit var tvDifferent: TextView

    private val textChangeDelay = 1000L
    private val textArray = arrayOf("different", "true", "creative","you")
    private var currentTextIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SplashBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgSplash = view.findViewById(R.id.img_splash)
        tvDifferent = view.findViewById(R.id.tv_different)

        // Create a scale animation
        val scaleAnimation = ScaleAnimation(
            0.2f, 1.0f,
            0.2f, 1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )

        scaleAnimation.duration = 2000

        scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                startTextChangeSequence()
                Handler().postDelayed({
                    findNavController().navigate(R.id.action_splash_to_splashloading)
                }, 6000)
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
        imgSplash.startAnimation(scaleAnimation)

    }



    private fun startTextChangeSequence() {
        tvDifferent.visibility = View.VISIBLE
        val handler = Handler()
        handler.postDelayed({
            tvDifferent.text = textArray[currentTextIndex]
            currentTextIndex = (currentTextIndex + 1) % textArray.size
            startTextChangeSequence()
        }, textChangeDelay)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}