package com.nikhilproject.domain.model

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val gender: String,
    val phoneNo: Long
)
