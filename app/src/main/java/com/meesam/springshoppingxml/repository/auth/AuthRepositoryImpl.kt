package com.meesam.springshoppingxml.repository.auth

import com.meesam.springshoppingxml.api.AuthApiService
import com.meesam.springshoppingxml.models.AuthLoginResponse
import com.meesam.springshoppingxml.models.AuthRegistrationResponse
import com.meesam.springshoppingxml.models.LoginRequest
import com.meesam.springshoppingxml.models.RegiterRequest
import jakarta.inject.Inject
import retrofit2.Response

class AuthRepositoryImpl @Inject constructor(private val authApiService: AuthApiService) :
    AuthRepository {
    override suspend fun loginUser(loginRequest: LoginRequest): Response<AuthLoginResponse> {
        try {
            return authApiService.login(loginRequest)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun registerUser(registerRequest: RegiterRequest): Response<AuthRegistrationResponse> {
        try {
            return authApiService.register(registerRequest)
        } catch (ex: Exception) {
            throw ex
        }
    }
}