package com.meesam.springshoppingxml.models

import java.time.LocalDateTime

data class AuthRegistrationResponse(
    val id: Long,
    val name: String,
    val email: String,
    val role : String,
    val dob: LocalDateTime,
    val lastLoginAt: LocalDateTime
)
