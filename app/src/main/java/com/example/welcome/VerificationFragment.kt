package com.example.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.welcome.databinding.FragmentLoginBinding
import com.example.welcome.databinding.FragmentVerificationBinding
import com.google.android.material.snackbar.Snackbar

class VerificationFragment : Fragment() {

    private var _binding: FragmentVerificationBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentVerificationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnResetPassword.setOnClickListener {
            val otpInput = binding.etOtpNumber.text.toString()

            if (isValidOTP(otpInput)) {
                findNavController().navigate(R.id.action_verificationFragment_to_verificationNewFragment)
                showToast("Reset New Password")
            } else {
                Snackbar.make(view, "Invalid OTP. Please enter a valid 6-digit OTP.", Snackbar.LENGTH_SHORT).show()
            }
//            findNavController().navigate(R.id.action_verificationFragment_to_verificationNewFragment)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    private fun isValidOTP(otp: String): Boolean {
        val otpPattern = "[0-9]{6}" // 6-digit OTP validation
        return otp.matches(otpPattern.toRegex())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}