package com.example.kaspresso

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kaspresso.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentLoginBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener { login() }
    }

    private fun isCredentialsValid(): Boolean =
        with(binding) {
            etEmail.text.toString().isNotBlank() && etPassword.text.toString().isNotBlank() &&
                    etEmail.text.toString().contains("@")
        }

    private fun login() {
        if (isCredentialsValid()) {
            with(binding) {
                if (etEmail.text.toString() == "admin@gmail.com" && etPassword.text.toString() == "1234") {
                    successLoggedIn()
                } else {
                    loginFailed()
                }
            }
        } else {
            Toast.makeText(activity, "Credentials is invalid", Toast.LENGTH_SHORT).show()
        }
    }

    private fun successLoggedIn() {
        with(binding) {
            Toast.makeText(activity, "Success logged in", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }
    }

    private fun loginFailed() {
        Toast.makeText(activity, "Login failed", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "LoginFragment"
        fun newInstance() = LoginFragment()
    }
}