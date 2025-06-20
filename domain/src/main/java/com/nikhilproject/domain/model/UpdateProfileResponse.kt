package com.nikhilproject.domain.model

data class UpdateProfileResponse(
    val data: User,
    val message: String,
    val status: Int,
    val user_msg: String
)