package com.meesam.springshoppingxml.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meesam.springshoppingxml.models.AuthLoginResponse
import com.meesam.springshoppingxml.models.LoginRequest
import com.meesam.springshoppingxml.repository.auth.AuthRepository
import com.meesam.springshoppingxml.states.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {
    private var _authUser = MutableStateFlow<UiState<String>>(UiState.Idle)
    val authUser : StateFlow<UiState<String>> = _authUser.asStateFlow()

    fun loginUser(loginRequest: LoginRequest){
        try {
            viewModelScope.launch {
                _authUser.value = UiState.Loading
                val response = authRepository.loginUser(loginRequest)
                if(response.isSuccessful && response.body() !=null){
                    _authUser.value = UiState.Success(response.body()!!.token)
                }else {
                    _authUser.value = UiState.Error(response.errorBody()?.string())
                }
            }
        }catch (e: SocketTimeoutException){
            _authUser.value = UiState.Error("Connection timed out. Please check your internet connection and try again.")
        }catch (e:java.io.IOException){
            _authUser.value = UiState.Error("Network error. Please check your internet connection.")
        }catch (ex: Exception){
            _authUser.value = UiState.Error(ex.message.toString())
        }
    }
}