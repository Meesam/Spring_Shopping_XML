package com.meesam.springshoppingxml.models

data class RegiterRequest(
    val name: String,
    val email: String,
    val password: String,
    val role : String?,
    val dob: String,
)
