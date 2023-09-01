package com.example.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.welcome.databinding.FragmentLoginBinding
import com.example.welcome.databinding.FragmentVerificationNewBinding
import com.google.android.material.snackbar.Snackbar

class VerificationNewFragment : Fragment() {

    private var _binding: FragmentVerificationNewBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentVerificationNewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSet.setOnClickListener {

            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (isValidPassword(password) && isPasswordMatching(password, confirmPassword)) {
                findNavController().navigate(R.id.action_verificationNewFragment_to_loginFragment)
                showToast("Password set successfully")
            } else {
                Snackbar.make(view, "Invalid password or Confirm passwords do not match", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

    private fun isPasswordMatching(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}