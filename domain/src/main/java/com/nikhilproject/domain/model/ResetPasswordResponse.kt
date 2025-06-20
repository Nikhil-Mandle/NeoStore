package com.nikhilproject.domain.model

data class ResetPasswordResponse(
    val data: List<Any>,
    val message: String,
    val status: Int,
    val user_msg: String
)