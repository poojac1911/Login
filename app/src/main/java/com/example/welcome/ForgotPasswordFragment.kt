package com.example.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.welcome.databinding.FragmentForgotPasswordBinding
import com.google.android.material.snackbar.Snackbar

class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSend.setOnClickListener {
            val phoneNumber = binding.etPhoneNumber.text.toString()

            if (isValidPhoneNumber(phoneNumber)) {
                findNavController().navigate(R.id.action_forgotPasswordFragment_to_verificationFragment)
            } else {
                Snackbar.make(view, "Invalid Phone Number", Snackbar.LENGTH_SHORT).show()
            }

//            findNavController().navigate(R.id.action_forgotPasswordFragment_to_verificationFragment)
        }
    }
    private fun isValidPhoneNumber(phone: String): Boolean {
        val phonePattern = "[0-9]{10}"
        return phone.matches(phonePattern.toRegex())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}