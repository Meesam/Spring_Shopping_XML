package com.meesam.springshoppingxml.api

import com.meesam.springshoppingxml.models.AuthLoginResponse
import com.meesam.springshoppingxml.models.AuthRegistrationResponse
import com.meesam.springshoppingxml.models.LoginRequest
import com.meesam.springshoppingxml.models.RegiterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthLoginResponse>

    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegiterRequest): Response<AuthRegistrationResponse>
}