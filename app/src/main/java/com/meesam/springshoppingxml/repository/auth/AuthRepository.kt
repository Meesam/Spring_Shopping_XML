package com.meesam.springshoppingxml.repository.auth

import com.meesam.springshoppingxml.models.AuthLoginResponse
import com.meesam.springshoppingxml.models.AuthRegistrationResponse
import com.meesam.springshoppingxml.models.LoginRequest
import com.meesam.springshoppingxml.models.RegiterRequest
import retrofit2.Response

interface AuthRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Response<AuthLoginResponse>

    suspend fun registerUser(registerRequest: RegiterRequest): Response<AuthRegistrationResponse>

}