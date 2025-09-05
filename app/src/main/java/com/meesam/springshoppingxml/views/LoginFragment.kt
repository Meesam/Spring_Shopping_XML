package com.meesam.springshoppingxml.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.meesam.springshoppingxml.R
import com.meesam.springshoppingxml.databinding.FragmentLoginBinding
import com.meesam.springshoppingxml.models.LoginRequest
import com.meesam.springshoppingxml.states.UiState
import com.meesam.springshoppingxml.utils.TokenManager
import com.meesam.springshoppingxml.viewModels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    val authViewModel: AuthViewModel by viewModels()
    @Inject
    lateinit var tokenManager: TokenManager.TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.apply {
            progressBar.visibility = View.GONE
        }

        if(tokenManager.getToken() !=null){
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleResponse()
        binding.apply {
            etEmail.requestFocus()
            tvRegistration.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
            }
            btnLogin.setOnClickListener {
                if(etEmail.text.isNullOrEmpty()){
                    validEmail.text = "Please enter a valid email"
                    return@setOnClickListener
                }
                val request = LoginRequest(
                    email = etEmail.text.trim().toString(), password = etPassword.text.trim().toString()
                )
                authViewModel.loginUser(request)
            }
        }
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    private fun handleResponse() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel.authUser.collect { uiState ->
                    when (uiState) {
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                requireContext(), uiState.errorMessage, Toast.LENGTH_LONG
                            ).show()
                        }
                        is UiState.Idle -> {
                            binding.progressBar.visibility = View.GONE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is UiState.Success<*> -> {
                            Toast.makeText(
                                requireContext(), uiState.data.toString(), Toast.LENGTH_LONG
                            ).show()
                            tokenManager.saveToken(uiState.data.toString())
                            binding.progressBar.visibility = View.GONE
                            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                        }
                    }
                }
            }
        }
    }
}