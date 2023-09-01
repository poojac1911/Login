package com.example.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.welcome.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLogin.setOnClickListener {
            val emailOrPhone = binding.etEmailphoneNumber.text.toString()
            val password = binding.etPassword.text.toString()

            if (isValidEmailOrPhone(emailOrPhone) && isValidPassword(password))
            {
                showToast("Sucessfully Login")
                findNavController().navigate(R.id.action_loginFragment_to_splash)
            }
            else if(emailOrPhone.isEmpty()) {
                showToast("Enter the Email/ Phone Number")
            }else if(password.isEmpty()) {
                showToast("Enter the Password")
            }else {
                showToast("Invalid email/phone or password")
            }
//            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        binding.tvForgotPassword.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmailOrPhone(input: String): Boolean {
        return isValidEmail(input) || isValidPhoneNumber(input)
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    private fun isValidPhoneNumber(phone: String): Boolean {
        val phonePattern = "[0-9]{10}"
        return phone.matches(phonePattern.toRegex())
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}