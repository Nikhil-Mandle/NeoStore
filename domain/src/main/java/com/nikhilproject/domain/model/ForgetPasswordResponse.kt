package com.nikhilproject.domain.model

data class ForgetPasswordResponse(
    val message: String,
    val status: Int,
    val user_msg: String
)